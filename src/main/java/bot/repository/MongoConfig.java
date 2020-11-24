package bot.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final String MONGODB_VALUE="mongodb://localhost:27017";

    @Override
    protected @NotNull String getDatabaseName() {
        return "discord-base";
    }

    @Override
    public @NotNull MongoClient mongoClient(){
        return MongoClients.create(MONGODB_VALUE);
    }
}
