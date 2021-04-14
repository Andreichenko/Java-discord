package bot.commands.alias;

import bot.repositories.AliasEntityRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

public class AliasCreateCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasCreateCommand.class);

    private Set<String> allCurrentCommandNames;

    private Map<String, Command> commandNameToCommandMap;

    private final AliasEntityRepository aliasEntityRepository;

    public AliasCreateCommand(Set<String> allCurrentCommandNames, Map<String, Command> commandNameToCommandMap, AliasEntityRepository aliasEntityRepository) {
        this.allCurrentCommandNames = allCurrentCommandNames;
        this.commandNameToCommandMap = commandNameToCommandMap;
        this.aliasEntityRepository = aliasEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event) {

    }
}
