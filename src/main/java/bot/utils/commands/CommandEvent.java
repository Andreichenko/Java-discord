package bot.utils.commands;

import com.jagrosh.jdautilities.command.CommandClient;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.function.Consumer;

public interface CommandEvent {

    String getArgs();
    MessageReceivedEvent getEvent();
    CommandClient getClient();

    void linkId(Message message);
    void reply(String message, Consumer<Message> success, Consumer<Throwable> failure);
    void reply(MessageEmbed embed);
    void reply(String message);
    void reply(MessageEmbed embed, Consumer<Message> success);
    void reply(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure);
    void reply(String message, Consumer<Message> success);
    void reply(Message message);
}
