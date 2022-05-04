package bot.services;


import bot.utils.BotConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class BotService {

    public static final String COMMAND_PREFIX = "-";
    private final String discordBotKey;
    private final String ownerId;
    private final BotConfiguration botConfiguration;
    private AudioPlayerManager playerManager;

    @Autowired
    public BotService(BotConfiguration botConfiguration) {
        this.discordBotKey = botConfiguration.getDiscordKey();
        this.ownerId = botConfiguration.getOwnerId();
        this.botConfiguration = botConfiguration;
    }

    public void startBot() throws LoginException {
        playerManager = new DefaultAudioPlayerManager();
    }
}







