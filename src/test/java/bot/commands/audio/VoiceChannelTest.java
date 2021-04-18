package bot.commands.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.commands.audio.utils.VoiceChannelUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

public class VoiceChannelTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void canJoinVoiceChannelSuccessfully(){

        VoiceChannel mockVoiceChannel = mock(VoiceChannel.class);

        GuildVoiceState mockGuildVoiceState = mock(GuildVoiceState.class);
        when(mockGuildVoiceState.inVoiceChannel()).thenReturn(true);
        when(mockGuildVoiceState.getChannel()).thenReturn(mockVoiceChannel);

        AudioManager mockAudioManager = mock(AudioManager.class);

        ArgumentCaptor<AudioSendHandler> audioSendHandlerArgumentCaptor = ArgumentCaptor.forClass(AudioSendHandler.class);
        doAnswer(invocation -> null).when(mockAudioManager).setSendingHandler(audioSendHandlerArgumentCaptor.capture());

        ArgumentCaptor<VoiceChannel> voiceChannelArgumentCaptor = ArgumentCaptor.forClass(VoiceChannel.class);
        doAnswer(invocation -> null).when(mockAudioManager).openAudioConnection(voiceChannelArgumentCaptor.capture());

        Member mockMember = mock(Member.class);
        when(mockMember.getVoiceState()).thenReturn(mockGuildVoiceState);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);

        assertTrue(audioSendHandlerArgumentCaptor.getValue() instanceof AudioPlayerSendHandler);
        assertTrue(audioSendHandlerArgumentCaptor.getValue() instanceof TrackSchedulers);
        assertEquals(mockVoiceChannel, voiceChannelArgumentCaptor.getValue());
    }
}
