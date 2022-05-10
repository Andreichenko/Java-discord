package bot.api.controller;

import bot.api.dto.TriggerCommandDto;
import bot.entities.AliasEntity;
import bot.repositories.AliasEntityRepository;
import bot.services.BotService;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

        if (command == null) {
            // try getting an alias
            AliasEntity aliasEntity = aliasEntityRepository.findByServerIdAndName(triggerCommandDto.getGuildId(),
                    triggerCommandDto.getCommandName());

            if (aliasEntity != null) {
                triggerCommandDto.setCommandArgs(aliasEntity.getArgs());
                command = botService.getCommandWithName(aliasEntity.getCommand());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        // try setting it to the bot because it probably isn't needed
        if (StringUtils.isEmpty(triggerCommandDto.getAuthorId())) {
            triggerCommandDto.setAuthorId(botService.getJda().getSelfUser().getId());
        }

        if (!allRequiredVariablesPresent(triggerCommandDto) || command == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CommandEvent apiCommandEvent;
        try {
            apiCommandEvent = botService.createCommandEvent(triggerCommandDto);
        } catch(IllegalArgumentException e) {
            LOGGER.error("Error when creating command event", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        command.run(apiCommandEvent);
        LOGGER.info(String.format("Ran command %s with arguments %s in server %s", triggerCommandDto.getCommandName(),
                triggerCommandDto.getCommandArgs(), triggerCommandDto.getGuildId()));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private boolean allRequiredVariablesPresent(TriggerCommandDto triggerCommandDto) {
        if (StringUtils.isEmpty(triggerCommandDto.getCommandName())) {
            return false;
        }
        else if (StringUtils.isEmpty(triggerCommandDto.getAuthorId())) {
            return false;
        }
        else if (StringUtils.isEmpty(triggerCommandDto.getGuildId())) {
            return false;
        }
        else return !StringUtils.isEmpty(triggerCommandDto.getTextChannelId());
    }
}
