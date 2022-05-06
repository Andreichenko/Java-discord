package bot.services;


import bot.commands.alias.AliasCreateCommand;
import bot.commands.alias.AliasDeleteCommand;
import bot.commands.alias.AliasListCommand;
import bot.repositories.AliasEntityRepository;
import bot.utils.BotConfiguration;
import bot.utils.commands.Command;
import bot.utils.commands.CommandClientBuilder;
import com.jagrosh.jdautilities.command.CommandClient;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
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
    private final AliasEntityRepository aliasEntityRepository;
    private final AliasCreateCommand aliasCreateCommand;
    private final AliasDeleteCommand aliasDeleteCommand;
    private final AliasListCommand aliasListCommand;
    private JDA jda;
    private CommandClient client;



    @Autowired
    public BotService(BotConfiguration botConfiguration, AliasEntityRepository aliasEntityRepository, AliasCreateCommand aliasCreateCommand, AliasDeleteCommand aliasDeleteCommand, AliasListCommand aliasListCommand) {
        this.discordBotKey = botConfiguration.getDiscordKey();
        this.ownerId = botConfiguration.getOwnerId();
        this.botConfiguration = botConfiguration;
        this.aliasEntityRepository = aliasEntityRepository;
        this.aliasCreateCommand = aliasCreateCommand;
        this.aliasDeleteCommand = aliasDeleteCommand;
        this.aliasListCommand = aliasListCommand;
    }

    public void startBot() throws LoginException {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        CommandClientBuilder builder = new CommandClientBuilder();
    }

    public void shutdownBot(){
        this.jda.shutdown();
    }

    public Command getCommandWithName(String name){
        return null;
    }
}







