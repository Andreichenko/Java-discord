package bot.commands.alias;

import bot.listeners.CommandEventListener;
import bot.repository.EntityGuildHolderRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AliasDeleteCommands extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasDeleteCommands.class);

    private final CommandEventListener aliasCommandEventListener;

    private final EntityGuildHolderRepository entityGuildHolderRepository;

    public AliasDeleteCommands(CommandEventListener aliasCommandEventListener, EntityGuildHolderRepository entityGuildHolderRepository) {
        this.aliasCommandEventListener = aliasCommandEventListener;
        this.entityGuildHolderRepository = entityGuildHolderRepository;
    }

    @Override
    protected void execute(CommandEvent event) {
        String aliasToDelete = event.getArgs();

        String guildId = event.getGuild().getId();

    }
}
