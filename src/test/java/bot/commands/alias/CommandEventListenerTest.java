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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;

//will be run using mockito runner
//@RunWith(MockitoJUnitRunner.class)
public class CommandEventListenerTest {

//    private final Set<String> ALL_CURRENT_COMMAND_NAMES = new HashSet<>();
//    private final HashMap<String, Command> commandNameToCommandMap = new HashMap<>();
//
//
//    private final String GUILD_ID = "1872633";
//
//    @Before
//    public void init()
//    {
//        MockitoAnnotations.initMocks(this);
//        ALL_CURRENT_COMMAND_NAMES.add("play");
//        ALL_CURRENT_COMMAND_NAMES.add("pause");
//        ALL_CURRENT_COMMAND_NAMES.add("nowplaying");
//        ALL_CURRENT_COMMAND_NAMES.add("join");
//        ALL_CURRENT_COMMAND_NAMES.add("leave");
//
//        Command mockPlayCommand = mock(Command.class);
//
//        commandNameToCommandMap.put("play", mockPlayCommand);
//    }
//
//    @Test
//    public void testAliasCreateSomeSuccessfullyValueForMultiWordArguments(){
//
//        final String ALIAS_NAME = "rain";
//        final String ALIAS_COMMAND = "play";
//        final String ALIAS_ARGUMENTS = "rain song";
//
//        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
//
//
//        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);
//
//        CommandClient mockCommandClient = mock(CommandClient.class);
//        CommandEventListener aliasCommandEventListener = new CommandEventListener();
//        aliasCommandEventListener.setCommandClient(mockCommandClient);
//
////        AliasCreateCommands aliasCreateCommand = new AliasCreateCommands(aliasCommandEventListener);
//
//
//        CommandEvent mockCommandEvent = mock(CommandEvent.class);
//        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
//        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME + " " + ALIAS_COMMAND + " " + ALIAS_ARGUMENTS);
//        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
//        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);
//
//
//        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(GUILD_ID);
////        assertTrue(guildAliasHolder.doesAliasExistForCommand(ALIAS_NAME));
////        Alias alias = guildAliasHolder.getCommandWithAlias(ALIAS_NAME);
//
//
//
////        assertEquals(textChannelArgumentCaptor.getValue(), String.format(ALIAS_NAME, ALIAS_COMMAND, ALIAS_ARGUMENTS));
//
//    }
//
//


}
