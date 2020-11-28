package bot.commands.audio.utils;

import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeSearchProvider;
import com.sedmelluq.discord.lavaplayer.track.AudioItem;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.BasicAudioPlaylist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        AudioItem result = yt.loadSearchResult(argument, audioTrackInfo -> new YoutubeAudioTrack(audioTrackInfo, youtube));

        if (result instanceof BasicAudioPlaylist){

            BasicAudioPlaylist playlist = (BasicAudioPlaylist) result;
            List<AudioTrack> tracks = playlist.getTracks();

            LOGGER.info("Received {} results", tracks.size());
        }
        return null;

    }

    @Deprecated
    public static String getYoutubeThumbnail(AudioTrack audioTrack){
        return null;
    }

    static AudioTrack getRelatedVideo(String videoID, List<AudioTrack> history){
        return null;
    }

    private static boolean isAudioTrackOnHistory(String id, List<AudioTrack> history){
        for (AudioTrack historyAudioTrack : history){
            if (historyAudioTrack instanceof YoutubeAudioTrack && historyAudioTrack.getIdentifier().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
