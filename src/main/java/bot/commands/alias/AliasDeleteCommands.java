package bot.commands.alias;

import bot.listeners.CommandEventListener;
import bot.repository.EntityGuildHolderRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static bot.utils.ChannelTextResponses.ALIAS_DELETE_ALIAS_DOES_NOT_EXIST;

public class AliasDeleteCommands extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasDeleteCommands.class);

    private final CommandEventListener aliasCommandEventListener;

    private final EntityGuildHolderRepository entityGuildHolderRepository;

    public AliasDeleteCommands(CommandEventListener aliasCommandEventListener, EntityGuildHolderRepository entityGuildHolderRepository) {
        this.aliasCommandEventListener = aliasCommandEventListener;
        this.entityGuildHolderRepository = entityGuildHolderRepository;

        this.name = "aliasdelete";
        this.help = "Delete an alias with a specific name";
        this.aliases = new String[]{"ad"};
    }

    @Override
    protected void execute(CommandEvent event) {
        String aliasToDelete = event.getArgs();

        String guildId = event.getGuild().getId();
        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(guildId);

        if (!guildAliasHolder.doesAliasExistForCommand(aliasToDelete)){
            event.getChannel().sendMessage(String.format(ALIAS_DELETE_ALIAS_DOES_NOT_EXIST, aliasToDelete)).queue();
            return;
        }

        guildAliasHolder.removeCommandWithAlias(aliasToDelete);

       entityGuildHolderRepository.save(guildAliasHolder);

    }
}
