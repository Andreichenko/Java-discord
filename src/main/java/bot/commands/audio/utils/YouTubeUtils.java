package bot.commands.audio.utils;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class YouTubeUtils {
    private static final Logger LOGGER = LogManager.getLogger(YouTubeUtils.class);

    public static String getYoutubeThumbnail(AudioTrack np) {
        try {
            String youtubeUrl = np.getInfo().uri;

            List<NameValuePair> params = URLEncodedUtils.parse(new URI(youtubeUrl), StandardCharsets.UTF_8);
            String videoID = params.get(0).getValue();
            return "http://img.youtube.com/vi/" + videoID + "/0.jpg";
        } catch(URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    static AudioTrack getRelatedVideo(String videoID, List<AudioTrack> history, String youtubeApiKey) throws IOException,
            FriendlyException {
        LOGGER.info("finding related video for videoID {}", videoID);
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
        }).setApplicationName("bot").build();

        // Define the API request for retrieving search results.
        YouTube.Search.List search = youtube.search().list("id");

        //set the API key
        search.setKey(youtubeApiKey);
        search.setRelatedToVideoId(videoID);
        search.setEventType("none");
        search.setSafeSearch("none");
        search.setMaxResults(5L);
        search.setRegionCode("GB");

        // Restrict the search results to only include videos. See:
        // https://developers.google.com/youtube/v3/docs/search/list#type
        search.setType("video");

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        LOGGER.info("Found {} related videos", searchResultList.size());

        List<String> videosNotInHistory = new ArrayList<>();

        for (SearchResult searchResult : searchResultList) {
            String id = (String) ((ResourceId) searchResult.get("id")).get("videoId");

            if (!isAudioTrackOnHistory(id, history)) {
                videosNotInHistory.add(id);
            }
        }

        if (videosNotInHistory.isEmpty()){
            throw new IllegalArgumentException("Unable to find a related video - list was exhausted");
        }

        // check if this video is in the history
        for (String id : videosNotInHistory) {
            LOGGER.info("Found videoID {} as the related video", id);

            YoutubeAudioSourceManager youtubeAudioSourceManager = new YoutubeAudioSourceManager(true);
            try {
                return (AudioTrack) youtubeAudioSourceManager.loadTrackWithVideoId(id, true);
            } catch (FriendlyException exception) {
                LOGGER.error("Problem encountered whilst loading that video", exception);
            }
        }

        throw new IllegalArgumentException("Loading failed on all possible videos");
    }

    private static boolean isAudioTrackOnHistory(String id, List<AudioTrack> history) {
        for (AudioTrack historyAudioTrack : history) {
            if (historyAudioTrack instanceof YoutubeAudioTrack && historyAudioTrack.getIdentifier().equals(id)) {
                return true;
            }
        }
        return false;
    }
}