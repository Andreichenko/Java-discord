package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class SkipToCommand extends Command{

    /**
     * description
     */

    public SkipToCommand(){

        this.name = "skipto";
        this.help = "Skips to a certain position in the queue.";
    }

    @Override
    protected void execute(CommandEvent event) {

        AudioPlayerSendHandler audioPlayerSendHandler;

    }
}
