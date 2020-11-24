package bot.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guildholders")
public class GuildHolderEntity {

    public String guildId;
}
