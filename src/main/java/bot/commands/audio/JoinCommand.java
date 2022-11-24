package bot.commands.audio;


import bot.commands.audio.utils.VoiceChannelUtils;
import bot.repositories.OptionEntityRepository;
import bot.utils.TextChannelResponses;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

public class JoinCommand extends Command{

    //The audio player manager that the audio player will be created from
    private final AudioPlayerManager playerManager;
    private final String youtubeApiKey;
    private final OptionEntityRepository optionEntityRepository;

    public JoinCommand(AudioPlayerManager playerManager, String youtubeApiKey, OptionEntityRepository optionEntityRepository) {
        this.playerManager = playerManager;
        this.name = "join";
        this.help = "Joins the voice channel that the user is currently connected to";

        this.youtubeApiKey = youtubeApiKey;
        this.optionEntityRepository = optionEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            VoiceChannelUtils.joinVoiceChannel(event.getMember(), event.getGuild(), youtubeApiKey, playerManager,
                    optionEntityRepository);
        } catch(IllegalArgumentException e) {
            event.getChannel().sendMessage(TextChannelResponses.NOT_CONNECTED_TO_VOICE_MESSAGE).queue();
        }
        catch(InsufficientPermissionException e) {
            event.getChannel().sendMessage(TextChannelResponses.DONT_HAVE_PERMISSION_TO_JOIN_VOICE_CHANNEL).queue();
        }
    }
}
