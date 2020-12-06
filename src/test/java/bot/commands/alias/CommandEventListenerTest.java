package bot.commands.alias;


import bot.listeners.CommandEventListener;
import bot.repository.EntityGuildHolderRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static bot.utils.ChannelTextResponses.ALIAS_CREATED;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;

//will be run using mockito runner
@RunWith(MockitoJUnitRunner.class)
public class CommandEventListenerTest {

    private final Set<String> ALL_CURRENT_COMMAND_NAMES = new HashSet<>();
    private final HashMap<String, Command> commandNameToCommandMap = new HashMap<>();


    private final String GUILD_ID = "1872633";

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
        ALL_CURRENT_COMMAND_NAMES.add("play");
        ALL_CURRENT_COMMAND_NAMES.add("pause");
        ALL_CURRENT_COMMAND_NAMES.add("nowplaying");
        ALL_CURRENT_COMMAND_NAMES.add("join");
        ALL_CURRENT_COMMAND_NAMES.add("leave");

        Command mockPlayCommand = mock(Command.class);

        commandNameToCommandMap.put("play", mockPlayCommand);
    }

    @Test
    public void testAliasCreateSomeSuccessfullyValueForMultiWordArguments(){

        final String ALIAS_NAME = "rain";
        final String ALIAS_COMMAND = "play";
        final String ALIAS_ARGUMENTS = "rain song";

        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandClient mockCommandClient = mock(CommandClient.class);
        CommandEventListener aliasCommandEventListener = new CommandEventListener();
        aliasCommandEventListener.setCommandClient(mockCommandClient);

        AliasCreateCommands aliasCreateCommand = new AliasCreateCommands(aliasCommandEventListener,
                mock(EntityGuildHolderRepository.class));
        aliasCreateCommand.setAllCurrentCommandNames(ALL_CURRENT_COMMAND_NAMES);
        aliasCreateCommand.setCommandNameToCommandMap(commandNameToCommandMap);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME + " " + ALIAS_COMMAND + " " + ALIAS_ARGUMENTS);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);


        aliasCreateCommand.execute(mockCommandEvent);
        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(GUILD_ID);
        assertTrue(guildAliasHolder.doesAliasExistForCommand(ALIAS_NAME));
        Alias alias = guildAliasHolder.getCommandWithAlias(ALIAS_NAME);
        assertNotEquals(ALIAS_ARGUMENTS, alias.getAliasCommandArgs());
        assertEquals(textChannelArgumentCaptor.getValue(), String.format(ALIAS_CREATED, ALIAS_NAME, ALIAS_COMMAND,
                ALIAS_ARGUMENTS));
    }

    @Test
    public void testAliasCreatesSomeSuccessfullyForSingleWordArguments(){

        final String ALIAS_NAME = "anjunadeep";
        final String ALIAS_COMMAND = "play";
        final String ALIAS_ARGUMENTS = "marsh";

        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);
        CommandClient mockCommandClient = mock(CommandClient.class);

        CommandEventListener aliasCommandEventListener = new CommandEventListener();
        aliasCommandEventListener.setCommandClient(mockCommandClient);
        AliasCreateCommands aliasCreateCommand = new AliasCreateCommands(aliasCommandEventListener,
                mock(EntityGuildHolderRepository.class));
        aliasCreateCommand.setAllCurrentCommandNames(ALL_CURRENT_COMMAND_NAMES);
        aliasCreateCommand.setCommandNameToCommandMap(commandNameToCommandMap);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME + " " + ALIAS_COMMAND + " " + ALIAS_ARGUMENTS);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        aliasCreateCommand.execute(mockCommandEvent);
        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(GUILD_ID);
        assertTrue(guildAliasHolder.doesAliasExistForCommand(ALIAS_NAME));
        Alias alias = guildAliasHolder.getCommandWithAlias(ALIAS_NAME);

        assertNotEquals(ALIAS_ARGUMENTS, alias.getAliasCommandArgs());

        assertEquals(textChannelArgumentCaptor.getValue(), String.format(ALIAS_CREATED, ALIAS_NAME, ALIAS_COMMAND,
                ALIAS_ARGUMENTS));

    }


    public void testSomeAliasExecutesSuccessfully(){

        ArgumentCaptor<MessageReceivedEvent> messageReceivedEventArgumentCaptor =
                ArgumentCaptor.forClass(MessageReceivedEvent.class);

        final String ALIAS_NAME = "commands";
        final String CALL_ALIAS_MESSAGE = "-" + ALIAS_NAME;
        final String ALIAS_COMMAND = "play";
        final String ALIAS_ARGUMENTS = "marsh song";

        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        CommandClient mockCommandClient = mock(CommandClient.class);
        CommandEventListener aliasCommandEventListener = new CommandEventListener();
        aliasCommandEventListener.setCommandClient(mockCommandClient);

        AliasCreateCommands aliasCreateCommand = new AliasCreateCommands(aliasCommandEventListener,
                mock(EntityGuildHolderRepository.class));
        aliasCreateCommand.setAllCurrentCommandNames(ALL_CURRENT_COMMAND_NAMES);
        aliasCreateCommand.setCommandNameToCommandMap(commandNameToCommandMap);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME + " " + ALIAS_COMMAND + " " + ALIAS_ARGUMENTS);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        aliasCreateCommand.execute(mockCommandEvent);
        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(GUILD_ID);
        assertTrue(guildAliasHolder.doesAliasExistForCommand(ALIAS_NAME));
        Alias mockAlias = mock(Alias.class);
        // Lambda function when replace the alias it's not possible to mock out a command due to the final methods used
        doAnswer(invocation -> null).when(mockAlias).execute(messageReceivedEventArgumentCaptor.capture(), any());

        guildAliasHolder.addCommandWithAlias(ALIAS_NAME, mockAlias);

        assertEquals(textChannelArgumentCaptor.getValue(), String.format(ALIAS_CREATED, ALIAS_NAME, ALIAS_COMMAND,
                ALIAS_ARGUMENTS));

        MessageReceivedEvent mockMessageReceivedEvent = mock(MessageReceivedEvent.class);

        when(mockMessageReceivedEvent.getAuthor()).thenReturn(mock(User.class));
        when(mockMessageReceivedEvent.getAuthor().isBot()).thenReturn(false);
        when(mockMessageReceivedEvent.getMessage()).thenReturn(mock(Message.class));
        when(mockMessageReceivedEvent.getMessage().getContentRaw()).thenReturn("not command a message");
        when(mockMessageReceivedEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockMessageReceivedEvent.getGuild().getId()).thenReturn(GUILD_ID);

        aliasCommandEventListener.onMessageReceived(mockMessageReceivedEvent);

        when(mockMessageReceivedEvent.getMessage().getContentRaw()).thenReturn("a message");
        aliasCommandEventListener.onMessageReceived(mockMessageReceivedEvent);

        when(mockMessageReceivedEvent.getMessage().getContentRaw()).thenReturn("-play");
        aliasCommandEventListener.onMessageReceived(mockMessageReceivedEvent);

        when(mockMessageReceivedEvent.getMessage().getContentRaw()).thenReturn("-not-command");
        aliasCommandEventListener.onMessageReceived(mockMessageReceivedEvent);

    }


}
