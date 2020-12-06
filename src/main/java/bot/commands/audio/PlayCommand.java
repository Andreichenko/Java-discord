package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannel;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayCommand extends Command{


    private final AudioPlayerManager playerManager;

    private final Logger LOGGER = LogManager.getLogger(PlayCommand.class);

    public PlayCommand(AudioPlayerManager playerManager) {
        this.playerManager = playerManager;
        this.name = "play";
        this.help = "Please submit a song with the specified name or url.";
    }

    @Override
    protected void execute(CommandEvent event) {

        LOGGER.info("Play command triggered with message {}", event.getArgs());
        VoiceChannel.SearchAndPlaySong(event.getJDA(), event.getArgs(), event.getGuild().getId(),
                event.getChannel().getId(), event.getMember().getId(), false, playerManager);
    }


}
