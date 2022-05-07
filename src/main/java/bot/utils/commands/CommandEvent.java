package bot.utils.commands;

import com.jagrosh.jdautilities.command.CommandClient;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandEvent {

    String getArgs();
    MessageReceivedEvent getEvent();
    CommandClient getClient();

    void linkId(Message message);
}
