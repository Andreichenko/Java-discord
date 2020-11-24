package bot.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mapping")
public class Entity {

    @Field
    public String name;

    @Field
    public String commandName;

    @Field
    public String commandArgs;

    public Entity() {
    }

    public Entity(String name, String commandName, String commandArgs) {
        this.name = name;
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String commandArgs) {
        this.commandArgs = commandArgs;
    }
}
