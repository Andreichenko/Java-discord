package bot.commands.alias;

import bot.listeners.CommandEventListener;
import bot.repository.AliasEntityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.atomic.AtomicBoolean;

import static bot.utils.ChannelTextResponses.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
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

    @Test
    public void testFailsSuccessfullyWhenAliasDoesNotExist()
    {
        final String ALIAS_NAME = "name";
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        ArgumentCaptor<String> guildIdCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        GuildAlliasHolders mockGuildAlisHolder = mock(GuildAlliasHolders.class);

        CommandEventListener mockAliasCommandEventListener = mock(CommandEventListener.class);
        when(mockAliasCommandEventListener.getGuildAliasHolderForGuildWithId(guildIdCaptor.capture())).thenReturn(mockGuildAlisHolder);

        AliasDeleteCommands aliasDeleteCommand = new AliasDeleteCommands(mockAliasCommandEventListener, null);
        aliasDeleteCommand.execute(mockCommandEvent);

        assertEquals(String.format(ALIAS_DELETE_ALIAS_DOES_NOT_EXIST, ALIAS_NAME), textChannelArgumentCaptor.getValue());
        assertEquals(GUILD_ID, guildIdCaptor.getValue());
    }

    @Test
    public void testFailsSuccessfullyWhenAliasCantBeDeleted()
    {
        final String ALIAS_NAME = "name";
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        ArgumentCaptor<String> guildIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> aliasRemovalCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        GuildAlliasHolders mockGuildAlisHolder = mock(GuildAlliasHolders.class);
        doThrow(new IllegalArgumentException()).when(mockGuildAlisHolder).removeCommandWithAlias(aliasRemovalCaptor.capture());
        when(mockGuildAlisHolder.doesAliasExistForCommand(aliasRemovalCaptor.capture())).thenReturn(true);

        CommandEventListener mockAliasCommandEventListener = mock(CommandEventListener.class);
        when(mockAliasCommandEventListener.getGuildAliasHolderForGuildWithId(guildIdCaptor.capture())).thenReturn(mockGuildAlisHolder);

        AliasEntityRepository mockGuildAliasHolderEntityRepository = mock(AliasEntityRepository.class);

        AliasDeleteCommands aliasDeleteCommand = new AliasDeleteCommands(mockAliasCommandEventListener,
                mockGuildAliasHolderEntityRepository);
        aliasDeleteCommand.execute(mockCommandEvent);

        assertEquals(ALIAS_DELETE_ERROR_OCCURRED, textChannelArgumentCaptor.getValue());
        assertEquals(GUILD_ID, guildIdCaptor.getValue());
        aliasRemovalCaptor.getAllValues().forEach(alias -> assertEquals(ALIAS_NAME, alias));
    }

    @Test
    public void testSuccessfullyDeleteAlias()
    {
        final String ALIAS_NAME = "name";
        ArgumentCaptor<String> textChannelArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TextChannel mockTextChannel = createMockTextChannelWhereTextIsSentNoTyping(textChannelArgumentCaptor);

        ArgumentCaptor<String> guildIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> aliasRemovalCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn(ALIAS_NAME);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getId()).thenReturn(GUILD_ID);

        GuildAlliasHolders mockGuildAlisHolder = mock(GuildAlliasHolders.class);
        doAnswer(invocation -> null).when(mockGuildAlisHolder).removeCommandWithAlias(aliasRemovalCaptor.capture());
        when(mockGuildAlisHolder.doesAliasExistForCommand(aliasRemovalCaptor.capture())).thenReturn(true);

        CommandEventListener mockAliasCommandEventListener = mock(CommandEventListener.class);
        when(mockAliasCommandEventListener.getGuildAliasHolderForGuildWithId(guildIdCaptor.capture())).thenReturn(mockGuildAlisHolder);

        AtomicBoolean entitySaved = new AtomicBoolean(false);
        AliasEntityRepository mockGuildAliasHolderEntityRepository = mock(AliasEntityRepository.class);
        doAnswer(invocation ->
        {
            entitySaved.set(true);
            return null;
        }).when(mockGuildAliasHolderEntityRepository).save(any());

        AliasDeleteCommands aliasDeleteCommand = new AliasDeleteCommands(mockAliasCommandEventListener,
                mockGuildAliasHolderEntityRepository);
        aliasDeleteCommand.execute(mockCommandEvent);

        assertEquals(String.format(ALIAS_REMOVED, ALIAS_NAME), textChannelArgumentCaptor.getValue());
        assertEquals(GUILD_ID, guildIdCaptor.getValue());
        aliasRemovalCaptor.getAllValues().forEach(alias -> assertEquals(ALIAS_NAME, alias));
        assertTrue(entitySaved.get());
    }

}
