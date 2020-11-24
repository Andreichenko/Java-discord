package bot.entities;


import org.springframework.data.mongodb.core.mapping.Field;

public class Entity {

    @Field
    public String name;

    @Field
    public String commandName;

    @Field
    public String commandArgs;




}
