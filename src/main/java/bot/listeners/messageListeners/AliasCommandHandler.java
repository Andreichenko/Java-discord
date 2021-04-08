package bot.listeners.messageListeners;


import bot.repository.AliasEntityRepository;
import bot.services.BotService;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class AliasCommandHandler {

    private final Logger LOGGER = LogManager.getLogger(AliasCommandHandler.class);

    private BotService botService;

    private final AliasEntityRepository aliasEntityRepository;
    
}
