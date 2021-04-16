package bot.listeners.messages;

import bot.services.BotService;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class PrivateMessageHandler {

    private final Logger LOGGER = LogManager.getLogger(PrivateMessageHandler.class);

    public void handle(@NotNull MessageReceivedEvent event){

        LOGGER.debug("private message received");
        if (!dCommand(event)){

            LOGGER.info("Received private message from {} with content '{}'", event.getAuthor().getAsTag(),
                    event.getMessage().getContentRaw());
        }
    }

    private boolean dCommand(MessageReceivedEvent event){

        String rawContent = event.getMessage().getContentRaw();
        String[] queryParts = rawContent.split("\\s+");
        if (!rawContent.startsWith(BotService.COMMAND_PREFIX) || !queryParts[0].equals("-p")){
            return false;
        }

        // todo need to add some condition

        String userID = queryParts[1];
        queryParts[0] = "";
        queryParts[1] = "";

        String message = String.join(" ", queryParts).trim();

        User userToDm = event.getJDA().getUserById(userID);

        userToDm.openPrivateChannel().queue((channel) -> channel.sendMessage(message).queue());
        return true;
    }


    private void fail(MessageReceivedEvent event){
        event.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage("hello").queue());
    }

}
