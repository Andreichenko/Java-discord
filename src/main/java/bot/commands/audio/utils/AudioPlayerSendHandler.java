package bot.commands.audio.utils;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class AudioPlayerSendHandler implements AudioSendHandler {

    private final AudioPlayer audioPlayer;
    private final  TrackSchedulers trackScheduler;

    private AudioFrame lastFrame;

    public AudioPlayerSendHandler(@Nonnull AudioPlayer audioPlayer, @Nonnull TrackSchedulers trackScheduler) {
        this.audioPlayer = audioPlayer;
        this.trackScheduler = trackScheduler;
    }

    public TrackSchedulers getTrackScheduler() {
        return trackScheduler;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    @Override
    public boolean canProvide() {
        lastFrame = audioPlayer.provide();
        return lastFrame != null;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return ByteBuffer.wrap(lastFrame.getData());
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
