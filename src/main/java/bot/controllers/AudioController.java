package bot.controllers;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannelUtils;
import bot.services.BotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/audio")
@RestController
public class AudioController {

    private final BotService botService;
    private final Logger LOGGER = LogManager.getLogger(AudioController.class);

    public AudioController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/play")
    public ResponseEntity<String> addNewSong(@RequestParam boolean top, @RequestParam String argument,
                                             @RequestParam String guildId, @RequestParam String textChannelId,
                                             @RequestParam String memberId){
        try {
            VoiceChannelUtils.SearchAndPlaySong(botService.getJda(), argument, guildId, textChannelId, memberId, top,
                    botService.getAudioPlayerManager());
        }catch(IllegalArgumentException e){
            LOGGER.error("Error performing play command", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/skip")
    public ResponseEntity<String> addNewSong(@RequestParam String guildId){
        AudioPlayerSendHandler audioPlayerSendHandler;

        try{
            audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(botService.getJda(), guildId);
        }catch (IllegalArgumentException e){
            LOGGER.error("Error performing skip command", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (audioPlayerSendHandler != null){
            audioPlayerSendHandler.getAudioPlayer().stopTrack();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
