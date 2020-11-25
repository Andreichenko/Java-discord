package bot.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "guildholders")
public class GuildHolderEntity {

    @Id
    public String guildId;

    @Field
    public List<AliasEntity> aliasEntityList;

    public GuildHolderEntity() {
        this(null, new ArrayList<>());
    }

    public GuildHolderEntity(String guildId, List<AliasEntity> aliasEntityList) {
        this.guildId = guildId;
        this.aliasEntityList = aliasEntityList;
    }

    public void addNewEntity(AliasEntity aliasEntity){
        aliasEntityList.add(aliasEntity);
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public void removeEntityWithName(String commandName) throws IllegalArgumentException {

        AliasEntity aliasEntityToRemove = null;

        for (AliasEntity aliasEntity : aliasEntityList){
            if (Objects.equals(aliasEntity.getCommandName(), commandName)) {

                 aliasEntityToRemove = aliasEntity;
            }
        }

        if (aliasEntityToRemove == null){
            throw  new IllegalArgumentException(String.format("%s is not an alias that can be removed", commandName));
        }

        aliasEntityList.remove(aliasEntityToRemove);
    }

    public List<AliasEntity> getAliasEntityList(){
        return aliasEntityList;
    }
}
