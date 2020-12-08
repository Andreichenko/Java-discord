package bot.commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeCommand extends Command{

    private final Logger LOGGER = LogManager.getLogger(ResumeCommand.class);


    public ResumeCommand()
    {
        this.name = "resume";
        this.help = "Resume the song if it's been paused.";
    }

    @Override
    protected void execute(CommandEvent event){

    }
}
