package bot.commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

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

}
