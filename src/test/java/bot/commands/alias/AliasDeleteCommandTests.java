package bot.commands.alias;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import static bot.utils.ChannelTextResponses.ALIAS_DELETE_NONE_PROVIDED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;


@RunWith(MockitoJUnitRunner.class)
public class AliasDeleteCommandTests {

    private static final String GUILD_ID = "1234";


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFailsSuccessfullyWhenNoArgIsSent(){
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn("");
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);

        AliasDeleteCommands aliasDeleteCommand = new AliasDeleteCommands(null, null);
        aliasDeleteCommand.execute(mockCommandEvent);

        assertEquals(ALIAS_DELETE_NONE_PROVIDED, textChannelArgumentCaptor.getValue());
    }
}
