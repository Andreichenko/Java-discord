package bot.commands.text;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static bot.utils.TextChannelResponse.ECHO_COMMAND_NO_ARGS;

public class TextCommand extends Command{

    public TextCommand() {
        this.name = "stringtext";
        this.aliases = new String[]{"echo", "text"};
        this.help = "Sends a message with text passed as an argument";
    }

    @Override
    protected void execute(CommandEvent event) {

        String textReturn = event.getArgs();

        if (textReturn.isEmpty()){

            event.getChannel().sendMessage(ECHO_COMMAND_NO_ARGS).queue();
            return;
        }

        event.getChannel().sendMessage(textReturn).queue();
    }

}
