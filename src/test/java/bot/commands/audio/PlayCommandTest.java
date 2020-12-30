package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class PlayCommandTest {

    public static final String MOCK_VOICE_CHANNEL_ID = "mockVoiceChannelId";
    public static final String MOCK_GUILD_ID = "mockGuildId";
    public static final String MOCK_MEMBER_ID = "mockMemberId";
    public static final String MOCK_TEXT_CHANNEL_ID = "mockTextChannelId";
    public static final String EMPTY_ARGUMENT = "";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteWhereAudioChannelNeedsToBeJoined(){

        final String COMMAND_ARGUMENT = "shrek";

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<VoiceChannel> voiceChannelArgumentCaptor = ArgumentCaptor.forClass(VoiceChannel.class);

        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer,
                new TrackSchedulers());

    }

}
