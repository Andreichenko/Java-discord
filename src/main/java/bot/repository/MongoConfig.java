package bot.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * Primary configuration of MongoDB
 * @see org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
 * @see org.springframework.data.mongodb.config.MongoConfigurationSupport
 * @version 1.0
 */
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
