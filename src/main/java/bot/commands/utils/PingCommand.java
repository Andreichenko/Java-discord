package bot.commands.utils;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCommand extends Command {

    public PingCommand() {

        this.name = "ping";
        this.help = "Check the bot's response time to Discord";
    }

    @Override
    protected void execute(CommandEvent event) {

    }
}
