package bot.listeners.messages;

import bot.repositories.AliasEntityRepository;
import bot.services.BotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AliasCommandHandler {

    private final Logger LOGGER = LogManager.getLogger(AliasCommandHandler.class);

    private BotService botService;

    private final AliasEntityRepository aliasEntityRepository;

    public AliasCommandHandler(BotService botService, AliasEntityRepository aliasEntityRepository) {
        this.aliasEntityRepository = aliasEntityRepository;
    }
}
