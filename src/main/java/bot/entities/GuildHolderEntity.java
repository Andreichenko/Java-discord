package bot.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "guildholders")
public class GuildHolderEntity {

    public String guildId;

    public List<Entity> entityList;
}
