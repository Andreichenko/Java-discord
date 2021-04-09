package bot.commands.text;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.TextChannel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static bot.utils.TextChannelResponse.ECHO_COMMAND_NO_ARGS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;

@RunWith(MockitoJUnitRunner.class)
public class TestEchoTextCommand {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFailsWhenNoArgsAreSent(){

        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn("");
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);

        TextCommand echoTextCommand = new TextCommand();
        echoTextCommand.execute(mockCommandEvent);

        assertEquals(ECHO_COMMAND_NO_ARGS, textChannelArgumentCaptor.getValue());
    }

    public void testExecutesSuccessfully(){

        final String ARGS = "this is a message";
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn(ARGS);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);

        TextCommand echoTextCommand = new TextCommand();
        echoTextCommand.execute(mockCommandEvent);

        assertEquals(ARGS, textChannelArgumentCaptor.getValue());

    }
}
