package bot.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "bot")
@Configuration("BotConfiguration")
public class BotConfiguration {
    String discordKey;
    String userId;
    String ownerId;
    String youtubeApiKey;

    public String getDiscordKey() {
        return discordKey;
    }

    public void setDiscordKey(String discordKey) {
        this.discordKey = discordKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getYoutubeApiKey() {
        return youtubeApiKey;
    }

    public void setYoutubeApiKey(String youtubeApiKey) {
        this.youtubeApiKey = youtubeApiKey;
    }
}
