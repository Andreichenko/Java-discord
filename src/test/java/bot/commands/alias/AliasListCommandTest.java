package bot.commands.alias;

import bot.listeners.CommandEventListener;

import com.jagrosh.jdautilities.command.Command;
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

    public void testListReturnsMultipleAliases(){
        final String ALIAS_NAME_1 = "NAME_1";
        final String ALIAS_COMMAND_ARGUMENTS_1 = "COMMAND ARGUMENTS 1";
        final String COMMAND_NAME_1 = "PLAY";

        final String ALIAS_NAME_2 = "NAME_1";
        final String ALIAS_COMMAND_ARGUMENTS_2 = "COMMAND ARGUMENTS 2";
        final String COMMAND_NAME_2 = "PAUSE";

        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandClient mockCommandClient = mock(CommandClient.class);
        CommandEventListener aliasCommandEventListener = new CommandEventListener();
        aliasCommandEventListener.setCommandClient(mockCommandClient);

        GuildAlliasHolders guildAliasHolder = new GuildAlliasHolders();

        Command mockCommand1 = mock(Command.class);
        when(mockCommand1.getName()).thenReturn(COMMAND_NAME_1);
        Alias mockAlias1 = mock(Alias.class);
        when(mockAlias1.getAliasCommandArgs()).thenReturn(ALIAS_COMMAND_ARGUMENTS_1);
        when(mockAlias1.getAliasName()).thenReturn(ALIAS_NAME_1);
        when(mockAlias1.getCommand()).thenReturn(mockCommand1);

        Command mockCommand2 = mock(Command.class);
        when(mockCommand2.getName()).thenReturn(COMMAND_NAME_2);
        Alias mockAlias2 = mock(Alias.class);
        when(mockAlias2.getAliasCommandArgs()).thenReturn(ALIAS_COMMAND_ARGUMENTS_2);
        when(mockAlias2.getAliasName()).thenReturn(ALIAS_NAME_2);
        when(mockAlias2.getCommand()).thenReturn(mockCommand2);

    }

}
