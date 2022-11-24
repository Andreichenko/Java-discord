package bot.api.dto;

import java.io.Serializable;

public class TriggerCommandDto implements Serializable {

    String commandName;
    String commandArgs;
    String guildId;
    String authorId;
    String textChannelId;
    boolean silent;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTextChannelId() {
        return textChannelId;
    }

    public void setTextChannelId(String textChannelId) {
        this.textChannelId = textChannelId;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }
}
