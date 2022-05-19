package bot.commands.utils;


import bot.entities.OptionEntity;
import bot.repositories.OptionEntityRepository;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Locale;

@Component
public class OptionsCommand extends Command{

    private final OptionEntityRepository optionEntityRepository;
    private final HashMap<Boolean, String> enabledDisabledMap = new HashMap<>();

    public OptionsCommand(OptionEntityRepository optionEntityRepository){
        this.optionEntityRepository = optionEntityRepository;

        this.enabledDisabledMap.put(Boolean.FALSE, "disabled");
        this.enabledDisabledMap.put(Boolean.TRUE, "enabled");
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendTyping().queue();
        String guildId = event.getGuild().getId();
        String optionName = arguments[0].toLowerCase(Locale.ROOT);
        OptionEntity optionEntity = optionEntityRepository.findByServerIdAndName(guildId, optionName);
    }
}
