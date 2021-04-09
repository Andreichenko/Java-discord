package bot.services;


import bot.commands.alias.AliasCreateCommand;
import bot.commands.alias.AliasDeleteCommand;
import bot.commands.alias.AliasListCommand;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.security.auth.login.LoginException;
import java.util.Map;

@Service
public interface BotService {

    @Value("${DISCORD_BOT_KEY}")
    public String DISCORD_BOT_KEY = null;

    @Value("${OWNER_ID}")
    public String OWNER_ID = null;

    public JDA jda = null;
    public AudioPlayerManager playerManager = null;

    public static final String COMMAND_PREFIX = "-";

    public CommandClient client = null;

    public Map<String, Command> commandNameToCommandMap = null;

    public final MessageReceivedEventListener messageReceivedEventListener = null;

    public final AliasListCommand aliasListCommand = null;

    public final AliasDeleteCommand aliasDeleteCommand = null;

    public final AliasCreateCommand aliasCreateCommand = null;



    public void startBot() throws LoginException;

    public void shutdownBot();

    public JDA getJda();

    public AudioPlayerManager getAudioPlayerManager();

    public CommandClient getClient();

    public Command getCommandFromName(String commandName);
}
