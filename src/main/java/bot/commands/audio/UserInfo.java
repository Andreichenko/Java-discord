package bot.commands.audio;

import net.dv8tion.jda.api.JDA;

public class UserInfo {

    long channelId;

    String searchQuery;

    JDA jda;

    public UserInfo(long channelId, String searchQuery, JDA jda) {
        this.channelId = channelId;
        this.searchQuery = searchQuery;
        this.jda = jda;
    }

    public long getChannelId() {
        return channelId;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public JDA getJda() {
        return jda;
    }
}
