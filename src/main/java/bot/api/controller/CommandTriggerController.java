package bot.api.controller;

import bot.api.dto.TriggerCommandDto;
import bot.repositories.AliasEntityRepository;
import bot.services.BotService;
import bot.utils.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/command")
@RestController
public class CommandTriggerController {
    private static final Logger LOGGER = LogManager.getLogger(CommandTriggerController.class);
    private final BotService botService;
    private final AliasEntityRepository aliasEntityRepository;

    @Autowired
    public CommandTriggerController(BotService botService, AliasEntityRepository aliasEntityRepository) {
        this.botService = botService;
        this.aliasEntityRepository = aliasEntityRepository;
    }
    @PostMapping()
    public ResponseEntity<String> triggerCommand(@RequestBody TriggerCommandDto triggerCommandDto){
        Command command = botService.getCommandWithName(triggerCommandDto.getCommandName());
        return null;
    }
}
