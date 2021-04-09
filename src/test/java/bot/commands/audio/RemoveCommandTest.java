package bot.commands.audio;


import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static bot.utils.TextChannelResponse.REMOVE_COMMAND_NOT_A_NUMBER;
import static bot.utils.TextChannelResponse.REMOVE_COMMAND_NO_ARGUMENT;
import static bot.utils.TextChannelResponse.REMOVE_COMMAND_NO_TRACK_TO_REMOVE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRemovesTracksSuccessfully(){

        ArgumentCaptor<String> emoteArgumentCaptor = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        RestAction mockRestAction = mock(RestAction.class);
        doAnswer(invocation -> null).when(mockRestAction).queue();

        TrackSchedulers mockTrackScheduler = mock(TrackSchedulers.class);
        doAnswer(invocation -> null).when(mockTrackScheduler).remove(intArgumentCaptor.capture());

        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer, mockTrackScheduler);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn("3");
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getAudioManager()).thenReturn(mock(AudioManager.class));
        when(mockCommandEvent.getGuild().getAudioManager().getSendingHandler()).thenReturn(audioPlayerSendHandler);

        when(mockCommandEvent.getMessage()).thenReturn(mock(Message.class));
        when(mockCommandEvent.getMessage().addReaction(emoteArgumentCaptor.capture())).thenReturn(mockRestAction);

        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.execute(mockCommandEvent);

        assertEquals(Integer.valueOf(2), intArgumentCaptor.getValue());
        assertEquals(UnicodeMotion.THUMBS_UP, emoteArgumentCaptor.getValue());

    }

    @Test
    public void testSendsMessageWhenIndexOutOfBoundsIsThrown(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TrackSchedulers mockTrackScheduler = mock(TrackSchedulers.class);
        doThrow(new IndexOutOfBoundsException()).when(mockTrackScheduler).remove(intArgumentCaptor.capture());
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer, mockTrackScheduler);

        TextChannel mockTextChannel = mock(TextChannel.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn("0");
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getAudioManager()).thenReturn(mock(AudioManager.class));
        when(mockCommandEvent.getGuild().getAudioManager().getSendingHandler()).thenReturn(audioPlayerSendHandler);

        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);

        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.execute(mockCommandEvent);

        assertEquals(Integer.valueOf(-1), intArgumentCaptor.getValue());
        assertEquals(String.format(REMOVE_COMMAND_NO_TRACK_TO_REMOVE, 0), stringArgumentCaptor.getValue());
    }

    @Test
    public void testSendsMessageWhenAudioSendHandlerIsNull(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn("4");
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getAudioManager()).thenReturn(mock(AudioManager.class));
        when(mockCommandEvent.getGuild().getAudioManager().getSendingHandler()).thenReturn(null);

        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);

        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.execute(mockCommandEvent);

        assertEquals(String.format(REMOVE_COMMAND_NO_TRACK_TO_REMOVE, 4), stringArgumentCaptor.getValue());
    }

    @Test
    public void testSendsMessageWhenNoArgumentIsSent(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn("");

        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);

        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.execute(mockCommandEvent);

        assertEquals(REMOVE_COMMAND_NO_ARGUMENT, stringArgumentCaptor.getValue());
    }

    @Test
    public void testSendsMessageWhenArgumentThatIsNotANumberIsSent(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn("doo doo");

        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);

        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.execute(mockCommandEvent);

        assertEquals(String.format(REMOVE_COMMAND_NOT_A_NUMBER, "doo doo"), stringArgumentCaptor.getValue());
    }
}
