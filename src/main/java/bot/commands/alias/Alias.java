package bot.commands.alias;

import bot.entities.AliasEntity;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.annotation.Transient;

public class Alias extends AliasEntity{

    @Transient
    private final Logger LOGGER = LogManager.getLogger(Alias.class);

    @Transient
    private Command command;

    public Alias() {
    }

    public Alias(AliasEntity aliasEntity, Command command){
        this(aliasEntity.getAliasName(), aliasEntity.getAliasCommandArgs(), command);
    }


    public Alias(String aliasName, String aliasCommandArgs, Command command){
        super(aliasName, aliasCommandArgs, command.getName());
        this.command = command;
    }

    public void execute(MessageReceivedEvent event, CommandClient commandClient) {
        LOGGER.info("Executing alias for command {}", aliasName);
        command.run(new CommandEvent(event, aliasCommandArgs, commandClient));
    }
    public Alias(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getAliasName() {
        return aliasName;
    }
}
