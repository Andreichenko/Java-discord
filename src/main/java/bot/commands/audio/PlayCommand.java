package bot.commands.audio;

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
    }

    @Override
    protected void execute(CommandEvent event) {


    }


}
