package bot.commands.audio;

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

    }
}
