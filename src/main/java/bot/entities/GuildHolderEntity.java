package bot.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

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
}
