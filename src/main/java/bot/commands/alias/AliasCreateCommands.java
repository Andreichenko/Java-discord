package bot.commands.alias;

import bot.listeners.CommandEventListener;
import bot.repository.EntityGuildHolderRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * @apiNote this class implements the validation of commands that are written in the main class
 */
public class AliasCreateCommands extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasCreateCommands.class);

    private Set<String> allCurrentCommandNames;

    private final CommandEventListener commandEventListener;

    private final EntityGuildHolderRepository entityGuildHolderRepository;

    private HashMap<String, Command> commandNameToCommandMap;

    public AliasCreateCommands(CommandEventListener commandEventListener, EntityGuildHolderRepository entityGuildHolderRepository) {
        this.commandEventListener = commandEventListener;
        this.entityGuildHolderRepository = entityGuildHolderRepository;
    }

    @Override
    protected void execute(CommandEvent event) {

    }

    String sliceArgumentsToString(String[] arr, int start, int end){

        String[] slice = new String[end - start];
        return Arrays.toString(slice).replace(",", "").replace("[", "").replace("]", "");
    }
}
