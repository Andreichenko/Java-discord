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
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicBoolean;

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
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);

        assertEquals(ChannelTextResponses.DONT_HAVE_PERMISSION_TO_JOIN_VOICE_CHANNEL, stringArgumentCaptor.getValue());
        assertEquals(1, stringArgumentCaptor.getAllValues().size());
    }

    @Test
    public void testExecuteWithNoArgument(){

        AtomicBoolean messageQueued = new AtomicBoolean(false);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Answer<Void> messageQueuedAnswer = invocation ->
        {
            messageQueued.set(true);
            return null;
        };

        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereItErrorsOut(stringArgumentCaptor,
                MOCK_TEXT_CHANNEL_ID, MOCK_MEMBER_ID, MOCK_GUILD_ID, EMPTY_ARGUMENT, messageQueuedAnswer);

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);

        assertTrue(messageQueued.get());
        assertEquals(ChannelTextResponses.NO_ARGUMENT_PROVIDED_TO_PLAY_COMMAND, stringArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithNullGuildId() throws IllegalArgumentException{

        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereItErrorsOut(
                MOCK_TEXT_CHANNEL_ID, MOCK_MEMBER_ID, null, EMPTY_ARGUMENT);

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithEmptyGuildId() throws IllegalArgumentException
    {
        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereItErrorsOut(
                "mockTextChannelId", "mockMemberId", "", "");

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithNullTextChannelId() throws IllegalArgumentException
    {
        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereItErrorsOut(null,
                "mockMemberId", "mockGuildId", "");

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        PlayCommand playCommand = new PlayCommand(playerManager);
        playCommand.execute(mockCommandEvent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithNullMemberId() throws IllegalArgumentException{

    }

}
