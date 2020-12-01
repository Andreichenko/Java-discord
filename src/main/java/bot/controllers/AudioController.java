package bot.controllers;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannel;
import bot.services.DiscordBotService;
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

    private final Logger LOGGER = LogManager.getLogger(AudioController.class);
    private final DiscordBotService discordBotService;

    public AudioController(DiscordBotService discordBotService) {
        this.discordBotService = discordBotService;
    }

    @PostMapping("/play")
    public ResponseEntity<String> addNewSong(@RequestParam boolean top, @RequestParam String argument,
                                             @RequestParam String guildId, @RequestParam String textChannelId,
                                             @RequestParam String memberId){
        try {
            VoiceChannel.SearchAndPlaySong(discordBotService.getJda(), argument, guildId, textChannelId,
                    memberId, top, discordBotService.getAudioPlayerManager());
        }catch (IllegalArgumentException e){
            LOGGER.error("Error performing play command", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/skip")
    public ResponseEntity<String> addNewSong(@RequestParam String guildId){

        AudioPlayerSendHandler audioPlayerSendHandler;
        try {
            audioPlayerSendHandler = VoiceChannel.getAudioPlayerSendHandler(discordBotService.getJda(), guildId);
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
}
