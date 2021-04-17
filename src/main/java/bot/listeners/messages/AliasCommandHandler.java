package bot.listeners.messages;

import bot.entities.AliasEntity;
import bot.repositories.AliasEntityRepository;
import bot.services.BotService;
import com.jagrosh.jdautilities.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliasCommandHandler {

    private final Logger LOGGER = LogManager.getLogger(AliasCommandHandler.class);

    private BotService botService;

    private final AliasEntityRepository aliasEntityRepository;

    @Autowired
    public AliasCommandHandler(BotService botService, AliasEntityRepository aliasEntityRepository) {
        this.aliasEntityRepository = aliasEntityRepository;
    }

    public void handle(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }

        String guildID = event.getGuild().getId();

        String rawContent = event.getMessage().getContentRaw();

        if (rawContent.startsWith(BotService.COMMAND_PREFIX)){
            String query = rawContent.replace(BotService.COMMAND_PREFIX, "");
            String[] queryParts = query.split("\\s+");
            String commandName = queryParts[0];
            queryParts[0] = "";
            String commandExtraArgs = String.join(" ", queryParts).trim();
            AliasEntity aliasEntity = aliasEntityRepository.findByServerIdAndName(guildID, commandName);

            if (aliasEntity == null){
                return;
            }

            String args = aliasEntity.getArgs().equals("") ? commandExtraArgs : aliasEntity.getArgs();

            Command commandToExecute = botService.getCommandFromName(aliasEntity.getCommand());

        }
    }

    public void setBotService(BotService botService){

        this.botService = botService;
    }
}
