package bot.commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NOT_A_NUMBER;
import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NO_ARGUMENT;
import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NO_TRACK_TO_REMOVE;

public class RemoveCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(RemoveCommand.class);


    public RemoveCommand(){
        this.name = "remove";
        this.help = "Remove the requested song from the queue.";
    }

    @Override
    protected void execute(CommandEvent event) {

        String argument = event.getArgs();

        if (argument.isEmpty()){

            event.getChannel().sendMessage(REMOVE_COMMAND_NO_ARGUMENT).queue();
            return;
        }
    }
}
