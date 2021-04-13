package bot.services;


import bot.commands.alias.AliasCreateCommands;
import bot.commands.alias.AliasDeleteCommands;
import bot.commands.alias.AliasListCommands;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Map;

@Service
public class BotService {

    private String DISCORD_BOT_KEY;

    private String OWNER_ID;

    private AudioPlayerManager playerManager;

    private JDA jda;

    public static final String COMMAND_PREFIX = "-";

    private CommandClient client;

    private Map<String, Command> commandNameToCommandMap;

    public void startBot() throws LoginException {

        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

    }
}
