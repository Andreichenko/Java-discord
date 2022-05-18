package bot.commands.text;

import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DmCommand extends Command{
    public DmCommand() {
    }

    @Override
    protected void execute(CommandEvent event) {
        String rawContent = event.getMessage().getContentRaw();
    }
}

