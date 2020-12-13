package bot.commands.alias;

import bot.listeners.CommandEventListener;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import static bot.utils.ChannelTextResponses.NO_ALIASES_SET;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AliasListCommandTest {

    private final String GUILD_ID = "4123123";

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListReturnsWhenThereAreNoAliases()
    {
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandClient mockCommandClient = mock(CommandClient.class);
        CommandEventListener aliasCommandEventListener = new CommandEventListener();
        aliasCommandEventListener.setCommandClient(mockCommandClient);

        AliasListCommands aliasListCommand = new AliasListCommands(aliasCommandEventListener);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        aliasListCommand.execute(mockCommandEvent);

        assertEquals(NO_ALIASES_SET, textChannelArgumentCaptor.getValue());
    }


}
