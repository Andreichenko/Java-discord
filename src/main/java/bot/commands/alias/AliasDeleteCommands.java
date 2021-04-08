package bot.commands.alias;

import bot.listeners.CommandEventListener;
import bot.repository.AliasEntityRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static bot.utils.ChannelTextResponses.*;

public class AliasDeleteCommands extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasDeleteCommands.class);

    private final CommandEventListener aliasCommandEventListener;

    private final AliasEntityRepository aliasEntityRepository;

    public AliasDeleteCommands(CommandEventListener aliasCommandEventListener, AliasEntityRepository aliasEntityRepository) {
        this.aliasCommandEventListener = aliasCommandEventListener;
        this.aliasEntityRepository = aliasEntityRepository;

        this.name = "aliasdelete";
        this.help = "Delete an alias with a specific name";
        this.aliases = new String[]{"ad"};
    }

    @Override
    protected void execute(CommandEvent event) {
        String aliasToDelete = event.getArgs();

        if (aliasToDelete.isEmpty()){
            event.getChannel().sendMessage(ALIAS_DELETE_NONE_PROVIDED).queue();
            return;
        }

        String guildId = event.getGuild().getId();
        GuildAlliasHolders guildAliasHolder = aliasCommandEventListener.getGuildAliasHolderForGuildWithId(guildId);

        if (!guildAliasHolder.doesAliasExistForCommand(aliasToDelete)){
            event.getChannel().sendMessage(String.format(ALIAS_DELETE_ALIAS_DOES_NOT_EXIST, aliasToDelete)).queue();
            return;
        }

        try {
            guildAliasHolder.removeCommandWithAlias(aliasToDelete);

        }catch(IllegalArgumentException ex){

            LOGGER.error("Error occurred when deleting alias for guild {}", guildId, ex);
            event.getChannel().sendMessage(ALIAS_DELETE_ERROR_OCCURRED).queue();
            return;

        }



        event.getChannel().sendMessage(String.format(ALIAS_REMOVED, aliasToDelete)).queue();

    }
}
