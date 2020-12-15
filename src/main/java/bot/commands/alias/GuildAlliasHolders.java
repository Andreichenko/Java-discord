package bot.commands.alias;

import bot.entities.AliasEntity;
import bot.entities.GuildHolderEntity;
import com.jagrosh.jdautilities.command.CommandClient;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuildAlliasHolders extends GuildHolderEntity{

    @Transient
    private final HashMap<String, Alias> aliasNameToAliasObjects = new HashMap<>();

    public GuildAlliasHolders() { }

    public GuildAlliasHolders(String guildId){
        this(guildId, new ArrayList<>());
    }

    public GuildAlliasHolders(String guildId, List<AliasEntity> aliasEntityList){
        super(guildId, aliasEntityList);
    }

    public boolean doesAliasExistForCommand(String command){
        return aliasNameToAliasObjects.containsKey(command);
    }

    public void executeAlias(String command, MessageReceivedEvent event, CommandClient commandClient){
        Alias alias = aliasNameToAliasObjects.get(command);
        alias.execute(event, commandClient);
    }
    public void addCommandWithAlias(String command, Alias alias){
        aliasNameToAliasObjects.put(command, alias);
        super.addNewEntity(alias);
    }

    public void removeCommandWithAlias(String command){
        aliasNameToAliasObjects.remove(command);
        super.removeEntityWithName(command);

    }

    public Alias getCommandWithAlias(String command){

        return aliasNameToAliasObjects.get(command);
    }

    public HashMap<String, Alias> getAliasNameToAliasObject(){

        return aliasNameToAliasObjects;
    }

}
