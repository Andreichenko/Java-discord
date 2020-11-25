package bot.controllers;

import bot.services.DiscordBotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/audio")
@RestController
public class ControllerAudio {

    private final Logger LOGGER = LogManager.getLogger(ControllerAudio.class);
    private final DiscordBotService discordBotService;

    public ControllerAudio(DiscordBotService discordBotService) {
        this.discordBotService = discordBotService;
    }
}
