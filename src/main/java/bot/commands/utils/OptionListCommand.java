package bot.commands.utils;

import bot.entities.OptionEntity;
import bot.repositories.OptionEntityRepository;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static bot.commands.utils.OptionsCommand.OPTION_NAMES;
import static bot.utils.EmbedUtils.setRandomColour;

@Component
public class OptionListCommand extends Command {


    private final OptionEntityRepository optionEntityRepository;

    public OptionListCommand(OptionEntityRepository optionEntityRepository) {
        this.optionEntityRepository = optionEntityRepository;
        this.name = "optionlist";
        this.aliases = new String[]{"settingslist", "settinglist", "optionslist"};
        this.help = "Show a list of the current settings.";
    }

    @Override
    protected void execute(CommandEvent event) {

        event.getChannel().sendTyping().queue();

        HashMap<String, Boolean> nameToState = new HashMap<>();

        for (String option : OPTION_NAMES) {
            OptionEntity optionEntity = optionEntityRepository.findByServerIdAndName(event.getGuild().getId(), option);

            if (optionEntity == null || optionEntity.getOption()) {
                nameToState.put(option, true);

            } else {
                nameToState.put(option, false);
            }
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();

        setRandomColour(embedBuilder);
    }
}
