package bot.services.implement;

import bot.commands.audio.PlayCommand;
import bot.listeners.CommandEventListener;
import bot.repository.AliasEntityRepository;
import bot.services.BotService;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Value;

import javax.security.auth.login.LoginException;

public class BotImplement implements BotService {

    @Value("${DISCORD_BOT_KEY}")
    private String DISCORD_BOT_KEY;

    @Value("${OWNER_ID}")
    private String OWNER_ID;

    private JDA jda;
    private AudioPlayerManager playerManager;
    public static final String COMMAND_PREFIX = "-";
    @Override
    public void startLoad(AliasEntityRepository aliasEntityRepository) throws LoginException {

        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        CommandEventListener commandEventListener = new CommandEventListener();
        //??? need to add all commands listeners and discord listeners

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(COMMAND_PREFIX);
        builder.setActivity(null);
        builder.setOwnerId(OWNER_ID);
        builder.addCommands(new PlayCommand(playerManager));

        // need to implement more commands and alias
    }

    @Override
    public void off() {
        this.jda.shutdown();
    }

    @Override
    public JDA getJda() {

        return jda;
    }

    public AudioPlayerManager getAudioPlayerManager()
    {
        return playerManager;
    }
}
