package bot.commands.audio;


import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PlayTopCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void testExecute(){
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MessageEmbed> messageEmbedArgumentCaptor = ArgumentCaptor.forClass(MessageEmbed.class);

        AudioTrack mockAudioTrack = new YoutubeAudioTrack(new AudioTrackInfo("1", "", 999999999, "", true, ""),
                new YoutubeAudioSourceManager());

        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        when(mockAudioPlayer.getPlayingTrack()).thenReturn(mockAudioTrack);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer,
                new TrackSchedulers());
    }
}
