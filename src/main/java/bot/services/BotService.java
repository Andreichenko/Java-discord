package bot.services;


import bot.commands.alias.AliasCreateCommand;
import bot.commands.alias.AliasCreateCommands;
import bot.listeners.messages.AliasCommandHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
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

   //reworking........

    private Map<String, Command> commandNameToCommandMap;

    @Autowired
    public BotService( AliasCommandHandler aliasCommandHandler) {

        aliasCommandHandler.setBotService(this);
    }

    public void startBot() throws LoginException {

        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

    }

    public JDA getJda() {
        return jda;
    }

    public Command getCommandFromName(String commandName){

        return commandNameToCommandMap.get(commandName);
    }

    public void shutdownBot(){

    }

    public CommandClient getClient() {
        return client;
    }

    public AudioPlayerManager getPlayerManager() {
        return playerManager;
    }
}
