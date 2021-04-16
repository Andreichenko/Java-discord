package bot.listeners.messages;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class MessageReceivedEventListener extends ListenerAdapter{

    private final AliasCommandHandler aliasCommandHandler;

    private final PrivateMessageHandler privateMessageEventListener;

    @Autowired
    public MessageReceivedEventListener(AliasCommandHandler aliasCommandHandler){
        this.aliasCommandHandler = aliasCommandHandler;
        this.privateMessageEventListener = new PrivateMessageHandler();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        if (event.isFromType(ChannelType.PRIVATE)){
            privateMessageEventListener.handle(event);
        }else {
            aliasCommandHandler.handle(event);
        }

    }

}
