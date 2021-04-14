package bot.commands.alias;

import bot.repositories.AliasEntityRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static bot.utils.ChannelTextResponses.HOW_TO_MAKE_ALIAS;
import static bot.utils.ChannelTextResponses.NEED_MORE_ARGUMENTS_TO_CREATE_AN_ALIAS;

public class AliasCreateCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(AliasCreateCommand.class);

    private Set<String> allCurrentCommandNames;

    private Map<String, Command> commandNameToCommandMap;

    private final AliasEntityRepository aliasEntityRepository;

    @Autowired
    public AliasCreateCommand(AliasEntityRepository aliasEntityRepository) {
        this.name = "aliascreate";
        this.aliases = new String[]{"alias", "ac"};
        this.help = "Create a new alias for a command. Created using " + HOW_TO_MAKE_ALIAS;
        this.aliasEntityRepository = aliasEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendTyping().queue();

        String[] arguments = event.getArgs().split("\\s+");

        if (arguments.length < 2){
            event.getChannel().sendMessage(NEED_MORE_ARGUMENTS_TO_CREATE_AN_ALIAS).queue();
            return;
        }

        String aliasName = arguments[0].toLowerCase();
        String aliasCommand = arguments[1].toLowerCase();
        String aliasCommandArguments = arguments.length < 3 ? " " : sliceArgumentsToString(arguments, 2, arguments.length);
    }

    String sliceArgumentsToString(String[] arr, int start, int end){
        String[] slice = new String[end - start];
        if (slice.length >= 0) System.arraycopy(arr, start, slice, 0, slice.length);
        return Arrays.toString(slice).replace(",", "").replace("[", "").replace("]", "");

    }

    public void setAllCurrentCommandNames(Set<String> allCurrentCommandNames) {
        this.allCurrentCommandNames = allCurrentCommandNames;
    }

    public void setCommandNameToCommandMap(Map<String, Command> commandNameToCommandMap) {
        this.commandNameToCommandMap = commandNameToCommandMap;
    }
}
