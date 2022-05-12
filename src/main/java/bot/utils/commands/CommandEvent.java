package bot.utils.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.function.Consumer;

public interface CommandEvent {

    String getArgs();

    MessageReceivedEvent getEvent();

    CommandClient getClient();

    void linkId(Message message);

    void reply(String message);

    void reply(String message, Consumer<Message> success);

    void reply(String message, Consumer<Message> success, Consumer<Throwable> failure);

    void reply(MessageEmbed embed);

    void reply(MessageEmbed embed, Consumer<Message> success);

    void reply(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure);

    void reply(Message message);

    void reply(Message message, Consumer<Message> success);

    void reply(Message message, Consumer<Message> success, Consumer<Throwable> failure);

    void reply(File file, String filename);

    void reply(String message, File file, String filename);

    void replyFormatted(String format, Object... args);

    void replyOrAlternate(MessageEmbed embed, String alternateMessage);

    void replyOrAlternate(String message, File file, String filename, String alternateMessage);

    void replyInDm(String message);

    void replyInDm(String message, Consumer<Message> success);

    void replyInDm(String message, Consumer<Message> success, Consumer<Throwable> failure);

    void replyInDm(MessageEmbed embed);

    void replyInDm(MessageEmbed embed, Consumer<Message> success);

    void replyInDm(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure);

    void replyInDm(Message message);

    void replyInDm(Message message, Consumer<Message> success);

    void replyInDm(Message message, Consumer<Message> success, Consumer<Throwable> failure);

    void replyInDm(String message, File file, String filename);

    void replySuccess(String message);

    void replySuccess(String message, Consumer<Message> queue);

    void replyWarning(String message);

    void replyWarning(String message, Consumer<Message> queue);

    void replyError(String message);

    void replyError(String message, Consumer<Message> queue);

    void reactSuccess();

    void reactWarning();

    void reactError();

    void async(Runnable runnable);

    SelfUser getSelfUser();

    Member getSelfMember();

    boolean isOwner();

    User getAuthor();

    MessageChannel getChannel();

    ChannelType getChannelType();

    Guild getGuild();

    JDA getJDA();

    Member getMember();

    Message getMessage();

    PrivateChannel getPrivateChannel();

    long getResponseNumber();

    TextChannel getTextChannel();

    boolean isFromType(ChannelType channelType);

    void setArgs(String s);

}
