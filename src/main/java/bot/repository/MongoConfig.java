package bot.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.lang.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final String MONGODB_VALUE="mongodb://localhost:27017";

    @Override
    protected @NonNull String getDatabaseName() {
        return "discord-base";
    }

    @Override
    public MongoClient mongoClient(){
        return MongoClients.create(MONGODB_VALUE);
    }
}
