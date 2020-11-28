package bot.commands.audio.utils;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeSearchProvider;
import com.sedmelluq.discord.lavaplayer.track.AudioItem;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.BasicAudioPlaylist;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class YouTubeUtils {

    private static final Logger LOGGER = LogManager.getLogger(YouTubeUtils.class);

    @Deprecated
    static AudioTrack searchForVideo(String argument) throws IllegalAccessException{

        if (argument.startsWith("ytsearch:")){

            argument = argument.replace("ytsearch:", "");

        }
        LOGGER.info("Searching for {}", argument);

        YoutubeAudioSourceManager youtube = new YoutubeAudioSourceManager(true);

        YoutubeSearchProvider yt = new YoutubeSearchProvider();

        // Lambda function for creating new Object from YoutubeAudioTrack with audioTrackInfo parameters and
        // YoutubeAudioSourceManager with true trigger
        AudioItem result = yt.loadSearchResult(argument, audioTrackInfo -> new YoutubeAudioTrack(audioTrackInfo, youtube));

        if (result instanceof BasicAudioPlaylist){

            BasicAudioPlaylist playlist = (BasicAudioPlaylist) result;

            List<AudioTrack> tracks = playlist.getTracks();

            LOGGER.info("Received {} results", tracks.size());

            if (tracks.size() > 0){

                return tracks.get(0);
            } else {
                return null;
            }
        } else {

            if (result instanceof AudioReference){

                LOGGER.error("Trying to search for a video but the result is an AudioReference, uri is {}, identifier is " +
                        "{}", ((AudioReference) result).getUri(), ((AudioReference) result).getIdentifier());
            }

            throw new IllegalAccessException("YouTube Search Result is not instance of BasicAudioPlaylist " + result.getClass().toString());
        }

    }


    public static String getYoutubeThumbnail(AudioTrack audioTrack){

        try {
            String youtubeUrl = audioTrack.getInfo().uri;

            // Create a list of objects from URL and parse it
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(youtubeUrl), StandardCharsets.UTF_8);

            String videoID = params.get(0).getValue();

            return "http://img.youtube.com/vi/" + videoID + "/0.jpg";

        } catch (URISyntaxException e) {

            e.printStackTrace();

            return "";
        }

    }

    static AudioTrack getRelatedVideo(String videoID, List<AudioTrack> history) throws IOException {

        LOGGER.info("finding related video for videoID {}", videoID);
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request ->
        { }).setApplicationName("bot").build(); // some Lambda with HTTP request builder get ro YB builder with new application
        // Define the API request for retrieving search results.
        YouTube.Search.List search = youtube.search().list("id"); //with some exception
        // ?????????????????????????????????? how????????
        YoutubeAudioSourceManager youtubeAudioSourceManager = new YoutubeAudioSourceManager(true);
        return null;
    }

    // track history from getter implementation

    private static boolean isAudioTrackOnHistory(String id, List<AudioTrack> history){

        for (AudioTrack historyAudioTrack : history){

            if (historyAudioTrack instanceof YoutubeAudioTrack && historyAudioTrack.getIdentifier().equals(id)) {

                return true;
            }
        }

        return false;
    }
}
