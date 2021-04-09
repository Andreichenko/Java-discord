package bot.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *  Save and load some information to mongo database
 */

@Document
public class AliasEntity {

    @Id
    private Long id;

    private String serverId;

    private String name;

    private String command;

    private String args;

    public AliasEntity() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getServerId()
    {
        return serverId;
    }

    public AliasEntity setServerId(String serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public AliasEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCommand()
    {
        return command;
    }

    public AliasEntity setCommand(String command) {
        this.command = command;
        return this;
    }

    public String getArgs()
    {
        return args;
    }

    public AliasEntity setArgs(String args) {
        this.args = args;
        return this;
    }
}
