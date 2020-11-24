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
    public List<Entity> entityList;

    public GuildHolderEntity() {
        this(null, new ArrayList<>());
    }

    public GuildHolderEntity(String guildId, List<Entity> entityList) {
        this.guildId = guildId;
        this.entityList = entityList;
    }

    public void addNewEntity(Entity entity){
        entityList.add(entity);
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public void removeEntityWithName(String commandName) throws IllegalArgumentException {

        Entity entityToRemove = null;

        for (Entity entity : entityList){
            if (Objects.equals(entity.getCommandName(), commandName)) {

                 entityToRemove = entity;
            }
        }

        if (entityToRemove == null){
            throw  new IllegalArgumentException(String.format("%s is not an alias that can be removed", commandName));
        }

        entityList.remove(entityToRemove);
    }

    public List<Entity> getEntityList(){
        return entityList;
    }
}
