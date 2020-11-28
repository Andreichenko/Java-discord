package bot.commands.audio.utils;

import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
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
