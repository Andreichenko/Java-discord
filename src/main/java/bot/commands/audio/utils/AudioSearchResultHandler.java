package bot.commands.audio.utils;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AudioSearchResultHandler implements AudioLoadResultHandler {

    private final Logger LOGGER = LogManager.getLogger(AudioSearchResultHandler.class);

    private final TrackSchedulers trackSchedulers;
    private final AudioPlayerSendHandler audioPlayerSendHandler;
    private final String argument;
    private final boolean playTop;
    private final MessageChannel channel;

    AudioSearchResultHandler(TrackSchedulers trackSchedulers, AudioPlayerSendHandler audioPlayerSendHandler,
                             MessageChannel channel, String argument, boolean playTop){

        this.trackSchedulers = trackSchedulers;
        this.audioPlayerSendHandler = audioPlayerSendHandler;
        this.argument = argument;
        this.playTop = playTop;
        this.channel = channel;
    }


    @Override
    public void trackLoaded(AudioTrack track) {

        queueTracksAndStartNextSongs(track);

    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist){

        if (playlist.isSearchResult()) {

            AudioTrack audioTrack = playlist.getTracks().get(0);
            queueTracksAndStartNextSongs(audioTrack);
            return;
        }

        for (AudioTrack track : playlist.getTracks()){
            trackSchedulers.queue(track, playTop);
        }

        channel.sendMessage(String.format("**Queued `%d` tracks**", playlist.getTracks().size())).queue();

        if (audioPlayerSendHandler.getAudioPlayer().getPlayingTrack() == null){
            AudioTrack track = trackSchedulers.nextTrack();
            audioPlayerSendHandler.getAudioPlayer().playTrack(track);
        }

    }

    @Override
    public void noMatches(){
       try {
           AudioTrack track = YouTubeUtils.searchForVideo(argument);
           if (track != null) {
               queueTracksAndStartNextSongs(track);
           } else {
               channel.sendMessage(String.format("%s didn't match a video", argument)).queue();
           }
       }catch (IllegalAccessException ex){
           channel.sendMessage(String.format("%s didn't match a video", argument)).queue();
           LOGGER.error("Couldn't find a match for {}", argument, ex);
       }

    }

    @Override
    public void loadFailed(FriendlyException throwable){

    }

    private void queueTracksAndStartNextSongs(AudioTrack track){

        long queueDurationInMilliSeconds = trackSchedulers.getDurationInMilliSeconds();
        EmbedBuilder embedBuilder = getAudioTrackMessage(track, trackSchedulers.getQueueSize(), queueDurationInMilliSeconds);
        channel.sendMessage(embedBuilder.build()).queue();

        if (audioPlayerSendHandler.getAudioPlayer().getPlayingTrack() == null){

            audioPlayerSendHandler.getAudioPlayer().playTrack(trackSchedulers.nextTrack());
        }

    }

    private EmbedBuilder getAudioTrackMessage(AudioTrack track, int queueSize, long queueDurationInMilliSeconds){
        return null;
    }
}
