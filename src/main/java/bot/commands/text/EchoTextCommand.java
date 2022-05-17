package bot.commands.text;

import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;

import static bot.utils.TextChannelResponses.ECHO_COMMAND_NO_ARGS;

public class EchoTextCommand extends Command {
    public EchoTextCommand() {
        this.name = "echoTest";

    }

    @Override
    protected void execute(CommandEvent event) {
        String textToReturn = event.getArgs();
        if (textToReturn.isEmpty()) {
            event.getChannel().sendMessage(ECHO_COMMAND_NO_ARGS).queue();
            return;
        }

        event.getChannel().sendMessage(textToReturn).queue();
    }
}
