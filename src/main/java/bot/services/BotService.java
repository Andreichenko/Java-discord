package bot.services;

import bot.commands.alias.AliasCreateCommand;
import bot.commands.alias.AliasDeleteCommand;
import bot.commands.alias.AliasListCommand;

import bot.api.dto.TriggerCommandDto;
import bot.repositories.AliasEntityRepository;
import bot.utils.BotConfiguration;
import bot.utils.commands.Command;
import bot.utils.commands.CommandClientBuilder;
import bot.utils.commands.CommandEvent;
import com.jagrosh.jdautilities.command.CommandClient;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import bot.utils.commands.impl.ApiMessage;
import bot.utils.commands.impl.ApiTextChannel;
import bot.utils.commands.impl.ApiCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

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

        builder.setPrefix(COMMAND_PREFIX)
                .setActivity(null)
                .setOwnerId(ownerId)
                .setAliasEntityRepository(aliasEntityRepository)
                .setAliasCreateCommand(aliasCreateCommand);

    }

    public void shutdownBot(){
        this.jda.shutdown();
    }

   // public Command getCommandWithName(String name){
   //     return null;
   // }

    public CommandEvent createCommandEvent(TriggerCommandDto triggerCommandDto) {
        User user = jda.getUserById(triggerCommandDto.getAuthorId());
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        TextChannel textChannel;
        if (triggerCommandDto.isSilent()) {
            textChannel = new ApiTextChannel();
        } else {
            textChannel = jda.getTextChannelById(triggerCommandDto.getTextChannelId());
        }
        MessageChannel messageChannel = null; // unsupported
        PrivateChannel privateChannel = null; // unsupported
        Message apiMessage = new ApiMessage("-" + triggerCommandDto.getCommandName() + " " + triggerCommandDto.getCommandArgs());

        Guild guild = jda.getGuildById(triggerCommandDto.getGuildId());
        if (guild == null) {
            throw new IllegalArgumentException("guild is null");
        }

        Member member = guild.getMember(user);
        if (member == null) {
            throw new IllegalArgumentException("member is null");
        }

        return null;
        //return new ApiCommandEvent(user, textChannel, messageChannel, privateChannel,
        //        apiMessage, member, jda, guild, ChannelType.TEXT,
        //        triggerCommandDto.getCommandArgs(), client);
    }


    public JDA getJda() {
        return jda;
    }

    public CommandClient getClient() {
        return client;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return playerManager;
    }


}







