package bot.api.controller;

import bot.api.dto.GuildDto;
import bot.api.dto.MemberDto;
import bot.api.dto.TextChannelDto;
import bot.services.BotService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/guilds")
@RestController
public class GuildInformationController {
    private static final Logger LOGGER = LogManager.getLogger(GuildInformationController.class);
    private final BotService botService;

    @Autowired
    public GuildInformationController(BotService botService){
        this.botService = botService;
    }



}
