package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.ChannelTextResponses;
import com.jagrosh.jdautilities.command.CommandEvent;
import bot.commands.audio.utils.TrackSchedulers;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static testUtils.AudioTestMocker.*;


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

        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereVoiceChannelNeedsToBeJoinedAudioGetsPlayed(stringArgumentCaptor,
                        MOCK_TEXT_CHANNEL_ID, MOCK_MEMBER_ID, MOCK_GUILD_ID, MOCK_VOICE_CHANNEL_ID, COMMAND_ARGUMENT,
                        audioPlayerSendHandler,
                        voiceChannelArgumentCaptor);

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);

        assertEquals(COMMAND_ARGUMENT, stringArgumentCaptor.getAllValues().get(1));
        assertEquals(MOCK_VOICE_CHANNEL_ID, voiceChannelArgumentCaptor.getValue().getId());

    }

    @Test
    public void testExecuteWhereAudioChannelNeedsToBeJoinedButCantBecauseMemberVoiceStateIsNull() {
        final String COMMAND_ARGUMENT = "shrek";

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereMemberNotInVoiceChannel(stringArgumentCaptor,
                        MOCK_TEXT_CHANNEL_ID, MOCK_MEMBER_ID, MOCK_GUILD_ID, COMMAND_ARGUMENT
                );

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);

        assertEquals(ChannelTextResponses.NOT_CONNECTED_TO_VOICE_MESSAGE, stringArgumentCaptor.getValue());
        assertEquals(1, stringArgumentCaptor.getAllValues().size());
    }

    @Test
    public void testExecuteWhereAudioChannelNeedsToBeJoinedButCantDueToInsufficientPermissions(){

        final String COMMAND_ARGUMENT = "shrek";

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer,
                new TrackSchedulers());

        CommandEvent mockCommandEvent =
                createMockCommandEventForPlayCommandWhereBotLacksPermissionToJoinVoiceChannel(stringArgumentCaptor,
                        MOCK_TEXT_CHANNEL_ID, MOCK_MEMBER_ID, MOCK_GUILD_ID, MOCK_VOICE_CHANNEL_ID, COMMAND_ARGUMENT,
                        audioPlayerSendHandler);
    }


}
