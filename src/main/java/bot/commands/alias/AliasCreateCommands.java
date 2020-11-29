package bot.commands.alias;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class AliasCreateCommands extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasCreateCommands.class);

    private Set<String> allCurrentCommandNames;

    @Override
    protected void execute(CommandEvent event) {

    }
}
