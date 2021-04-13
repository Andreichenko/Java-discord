package bot.listeners;

import bot.listeners.messages.AliasCommandHandler;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedEventListener extends ListenerAdapter {

    private final AliasCommandHandler aliasCommandHandler;

    public MessageReceivedEventListener(AliasCommandHandler aliasCommandHandler) {
        this.aliasCommandHandler = aliasCommandHandler;
    }
}
