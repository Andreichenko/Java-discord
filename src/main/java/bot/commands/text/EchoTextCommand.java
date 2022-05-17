package bot.commands.text;

import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;

public class EchoTextCommand extends Command {
    public EchoTextCommand() {
        this.name = "echoTest";

    }

    @Override
    protected void execute(CommandEvent event) {
        String textToReturn = event.getArgs();
    }
}
