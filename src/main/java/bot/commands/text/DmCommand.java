package bot.commands.text;

import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DmCommand extends Command{


    public DmCommand() {
        this.name = "dm";
        this.hidden = true;
        this.allowedInGuild = false;
        this.allowedInDm = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        String rawContent = event.getMessage().getContentRaw();
        String[] queryParts = rawContent.split("\\s+");

        if (queryParts.length < 3){
            fail(event);
            return;
        }

        String userID = queryParts[1];
        queryParts[0] = "";
        queryParts[1] = "";

        String message = String.join(" ", queryParts).trim();

        User userToDm = event.getJDA().getUserById(userID);

        if (userToDm == null){
            LOGGER.debug("User ID {} not found", userID);
            fail(event);
            return;
        }

        userToDm.openPrivateChannel().queue(channel -> channel.sendMessage(message).queue());
    }

    private void fail(CommandEvent event){
        event.getAuthor().openPrivateChannel().queue(channel -> channel.sendMessage("noob").queue());
    }
}

