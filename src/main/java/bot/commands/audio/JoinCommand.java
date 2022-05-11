package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannelUtils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

/**
 * The audio player manager that the audio player will be created from
 */
public class JoinCommand extends Command{

    private final AudioPlayerManager playerManager;

    public JoinCommand(AudioPlayerManager playerManager){
        this.playerManager = playerManager;
        this.name = "join";
        this.help = "Joins the voice channel that the user is currently connected to";
    }

    @Override
    protected void execute(CommandEvent event) {

        try {
            VoiceChannelUtils.joinVoiceChannel(event.getMember(), event.getGuild(), playerManager);

        } catch(IllegalArgumentException e) {

            event.getChannel().sendMessage(TextChannelResponses.NOT_CONNECTED_TO_VOICE_MESSAGE).queue();

        } catch(InsufficientPermissionException e) {

            event.getChannel().sendMessage(TextChannelResponses.DONT_HAVE_PERMISSION_TO_JOIN_VOICE_CHANNEL).queue();
        }
    }

}
