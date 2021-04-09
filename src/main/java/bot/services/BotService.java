package bot.services;


import bot.commands.alias.AliasCreateCommands;
import bot.commands.alias.AliasDeleteCommands;
import bot.commands.alias.AliasListCommands;
import bot.commands.audio.*;
import bot.commands.utils.PingCommand;
import bot.listeners.messageListeners.AliasCommandHandler;
import bot.listeners.messageListeners.MessageReceivedEventListener;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static net.dv8tion.jda.api.requests.GatewayIntent.DIRECT_MESSAGES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_EMOJIS;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGE_REACTIONS;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_PRESENCES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_VOICE_STATES;

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
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(COMMAND_PREFIX);
        builder.setActivity(null);
        builder.setOwnerId(OWNER_ID);
        builder.addCommands(new JoinCommand(playerManager), new PlayCommand(playerManager),
                new PlayTopCommand(playerManager), new QueueCommand(), new LeaveCommand(), new NowPlayingCommand(),
                new SkipSongCommand(), new ClearQueueCommand(), new RemoveCommand(), new SeekCommand(),
                new PingCommand(), new ShuffleCommand(), new SkipToCommand(), new RedditSearchCommand(),
                new PauseCommand(), new ResumeCommand(), new LoopCommand(), aliasCreateCommand, aliasListCommand,
                aliasDeleteCommand, new EchoTextCommand(), new WhisperTextCommand());
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
