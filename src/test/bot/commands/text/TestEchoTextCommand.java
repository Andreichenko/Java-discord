package bot.commands.text;


import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.entities.TextChannel;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static bot.utils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TestEchoTextCommand {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void testFailsWhenNoArgsAreSent(){
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn("");
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);

    }

}
