package bot.commands.alias;

import bot.entities.AliasEntity;
import com.jagrosh.jdautilities.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.annotation.Transient;

public class Alias extends AliasEntity{

    @Transient
    private final Logger LOGGER = LogManager.getLogger(Alias.class);

    private Command command;
}
