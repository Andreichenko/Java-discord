package bot.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "guildholders")
public class GuildHolderEntity {

    @Id
    public String guildId;

    @Field
    public List<Entity> entityList;
}
