package bot.services;


import bot.commands.alias.AliasCreateCommands;
import bot.commands.alias.AliasDeleteCommands;
import bot.commands.alias.AliasListCommands;
import bot.listeners.messageListeners.AliasCommandHandler;
import bot.listeners.messageListeners.MessageReceivedEventListener;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Map;

@Service
public interface BotService {

    @Value("${DISCORD_BOT_KEY}")
    private String DISCORD_BOT_KEY;

    @Value("${OWNER_ID}")
    private String OWNER_ID;

    private JDA jda;
    private AudioPlayerManager playerManager;

    public static final String COMMAND_PREFIX = "-";

    private CommandClient client;

    private Map<String, Command> commandNameToCommandMap;

    private final MessageReceivedEventListener messageReceivedEventListener;

    private final AliasListCommands aliasListCommand;

    private final AliasDeleteCommands aliasDeleteCommand;

    private final AliasCreateCommands aliasCreateCommand;

    @Autowired
    public BotService(AliasCreateCommands aliasCreateCommand, AliasDeleteCommands aliasDeleteCommand,
                      AliasListCommands aliasListCommand, AliasCommandHandler aliasCommandHandler,
                      MessageReceivedEventListener messageReceivedEventListener)
    {
        this.aliasCreateCommand = aliasCreateCommand;
        this.aliasDeleteCommand = aliasDeleteCommand;
        this.aliasListCommand = aliasListCommand;
        this.messageReceivedEventListener = messageReceivedEventListener;

    }

    public void startBot() throws LoginException{

    }

    public void shutdownBot(){
        this.jda.shutdown();
    }

    public JDA getJda(){

    }

    public AudioPlayerManager getAudioPlayerManager(){
        return playerManager;
    }

    public CommandClient getClient(){
        return client;
    }

    public Command getCommandFromName(String commandName){
        return commandNameToCommandMap.get(commandName);
    }
}
