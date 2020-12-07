package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannel;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PauseCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(PauseCommand.class);

    public PauseCommand(){
        this.name = "pause";
        this.help = "Pause the currently playing song.";
    }

    @Override
    protected void execute(CommandEvent event){

        VoiceChannel.setPauseStatusOnAudioPlayer(event.getGuild(), event.getChannel(), event.getMember(), true);


    }
}
