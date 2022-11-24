package bot.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *  Save and load some information to postgreesql database
 */

@Entity
public class AliasEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serverId;

    private String name;

    private String command;

    private String args;

    public AliasEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getServerId() {
        return serverId;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public String getArgs() {
        return args;
    }

    public AliasEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AliasEntity setServerId(String serverId) {
        this.serverId = serverId;
        return this;
    }

    public AliasEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AliasEntity setCommand(String command) {
        this.command = command;
        return this;
    }

    public AliasEntity setArgs(String args) {
        this.args = args;
        return this;
    }
}
