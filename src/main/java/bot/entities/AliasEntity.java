package bot.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *  Save and load some information to mongo database
 */

@Document(collection = "mapping")
public class AliasEntity {

    @Field
    public String commandName;

    @Field
    public String aliasName;

    @Field
    public String aliasCommandArgs;

    public AliasEntity() {
    }

    public AliasEntity(String commandName, String aliasName, String aliasCommandArgs) {
        this.commandName = commandName;
        this.aliasName = aliasName;
        this.aliasCommandArgs = aliasCommandArgs;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasCommandArgs() {
        return aliasCommandArgs;
    }

    public void setAliasCommandArgs(String aliasCommandArgs) {
        this.aliasCommandArgs = aliasCommandArgs;
    }
}
