package bot.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mapping")
public class Entity {

    @Field
    public String name;

    @Field
    public String commandName;

    @Field
    public String commandArgs;

    public Entity() {
    }

}
