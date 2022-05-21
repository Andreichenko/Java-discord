package bot.commands.utils;


import bot.entities.OptionEntity;
import bot.repositories.OptionEntityRepository;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import static bot.utils.OptionsCommands.AUTOPLAY_HELP;
import static bot.utils.OptionsCommands.AUTOPLAY_NAME;
import static bot.utils.TextChannelResponses.NEED_MORE_ARGUMENTS_TO_SET_OPTION;
import static bot.utils.TextChannelResponses.NOT_VALID_OPTION;


@Component
public class OptionsCommand extends Command{

    static final String[] OPTION_NAMES = new String[]{AUTOPLAY_NAME};
    private final OptionEntityRepository optionEntityRepository;
    private final HashMap<Boolean, String> enabledDisabledMap = new HashMap<>();

    public OptionsCommand(OptionEntityRepository optionEntityRepository){
        this.optionEntityRepository = optionEntityRepository;

        this.enabledDisabledMap.put(Boolean.FALSE, "disabled");
        this.enabledDisabledMap.put(Boolean.TRUE, "enabled");
        this.name = "option";
        this.aliases = new String[]{"setting", "settings"};
        this.help = "Set options for the bot. Current Options:\n" +
                String.format("> %s - %s", AUTOPLAY_NAME, AUTOPLAY_HELP);
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendTyping().queue();
        // if an argument was not provided and there is an optionEntity then just invert whatever is currently set.
        // also we have to check that at least 3 arguments are specified
        //command is given as -options OPTIONS_NAME <optional true/false value>
        //get the arguments and extract them into the different parts
        String[] arguments = event.getArgs().split("\\s+");
        //check that at least 3 arguments are specified
        if (arguments.length < 1 || arguments[0].equals("")) {
            event.getChannel().sendMessage(NEED_MORE_ARGUMENTS_TO_SET_OPTION).queue();
            return;
        }

        String guildId = event.getGuild().getId();
        String optionName = arguments[0].toLowerCase(Locale.ROOT);

        if (!Arrays.asList(OPTION_NAMES).contains(optionName)) {
            event.getChannel().sendMessage(String.format(NOT_VALID_OPTION, optionName)).queue();
            return;
        }
        OptionEntity optionEntity = optionEntityRepository.findByServerIdAndName(guildId, optionName);
        if (optionEntity != null ){
            optionEntity = new OptionEntity();
        }else if (optionEntity == null){
           // event.getChannel().sendMessage(String.format("**%s has been %s.**", optionName, disabled_enabled_text)).queue();
        }
    }
}
