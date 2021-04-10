package bot.services.implement;

import bot.commands.alias.AliasCreateCommands;
import bot.commands.alias.AliasDeleteCommands;
import bot.commands.alias.AliasListCommands;
import bot.commands.audio.*;
import bot.commands.utils.PingCommand;
import bot.listeners.CommandEventListener;
import bot.repository.EntityGuildHolderRepository;
import bot.services.DiscordBotService;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Value;

import javax.security.auth.login.LoginException;

public class DiscordBotImplement implements DiscordBotService{

    @Value("${DISCORD_BOT_KEY}")
    private String DISCORD_BOT_KEY;

    @Value("${OWNER_ID}")
    private String OWNER_ID;

    private JDA jda;
    private AudioPlayerManager playerManager;
    public static final String COMMAND_PREFIX = "-";
    @Override
    public void startLoad(EntityGuildHolderRepository entityGuildHolderRepository) throws LoginException {

        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        CommandEventListener aliasCommandEventListener = new CommandEventListener();

        CommandEventListener commandEventListener = new CommandEventListener();

        AliasCreateCommands aliasCreateCommand = new AliasCreateCommands(aliasCommandEventListener,
                entityGuildHolderRepository);

        AliasDeleteCommands aliasDeleteCommand = new AliasDeleteCommands(aliasCommandEventListener,
                entityGuildHolderRepository);

        AliasListCommands aliasListCommand = new AliasListCommands(aliasCommandEventListener);
        //??? need to add all commands listeners and discord listeners

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(COMMAND_PREFIX);
        builder.setActivity(null);
        builder.setOwnerId(OWNER_ID);
        builder.addCommands(new PlayCommand(playerManager), new JoinCommand(playerManager),new PlayTopCommand(playerManager),
                new SkipSongCommand(), new ClearQueueCommand(), new RemoveCommand(), new SeekCommand(),
                new PingCommand(), new SkipToCommand(), new PauseCommand(), new ResumeCommand(),
                new LoopCommand(), new HistoryCommand());

        CommandClient client = builder.build();
        aliasCommandEventListener.setCommandClient(client);
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
