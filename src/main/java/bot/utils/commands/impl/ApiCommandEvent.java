package bot.utils.commands.impl;

import bot.utils.commands.CommandClient;
import bot.utils.commands.CommandEvent;
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

public class ApiCommandEvent implements CommandEvent{

    private final User user;
    private final TextChannel textChannel;
    private final MessageChannel messageChannel;
    private final PrivateChannel privateChannel;
    private final Message message;
    private final Member member;
    private final JDA jda;
    private final Guild guild;
    private final ChannelType channelType;
    private final String args;
    private final CommandClient commandClient;

    public ApiCommandEvent(User user, TextChannel textChannel, MessageChannel messageChannel, PrivateChannel privateChannel, Message message, Member member, JDA jda, Guild guild, ChannelType channelType, String args, CommandClient commandClient) {
        this.user = user;
        this.textChannel = textChannel;
        this.messageChannel = messageChannel;
        this.privateChannel = privateChannel;
        this.message = message;
        this.member = member;
        this.jda = jda;
        this.guild = guild;
        this.channelType = channelType;
        this.args = args;
        this.commandClient = commandClient;
    }

    @Override
    public String getArgs() {
        return args;
    }

    @Override
    public void setArgs(String s) {

    }

    @Override
    public MessageReceivedEvent getEvent(){
        return  null;
    }

    @Override
    public CommandClient getClient(){
        return commandClient;
    }

    @Override
    public void linkId(Message message){

    }

    @Override
    public void reply(String message) {}
    @Override
    public void reply(String message, Consumer<Message> success) {}

    @Override
    public void reply(String message, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void reply(MessageEmbed embed) {

    }

    @Override
    public void reply(MessageEmbed embed, Consumer<Message> success) {

    }

    @Override
    public void reply(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void reply(Message message) {

    }

    @Override
    public void reply(Message message, Consumer<Message> success) {

    }

    @Override
    public void reply(Message message, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void reply(File file, String filename) {

    }

    @Override
    public void reply(String message, File file, String filename) {

    }

    @Override
    public void replyFormatted(String format, Object... args) {

    }

    @Override
    public void replyOrAlternate(MessageEmbed embed, String alternateMessage) {

    }

    @Override
    public void replyOrAlternate(String message, File file, String filename, String alternateMessage) {

    }

    @Override
    public void replyInDm(String message) {

    }

    @Override
    public void replyInDm(String message, Consumer<Message> success) {

    }

    @Override
    public void replyInDm(String message, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void replyInDm(MessageEmbed embed) {

    }

    @Override
    public void replyInDm(MessageEmbed embed, Consumer<Message> success) {

    }

    @Override
    public void replyInDm(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void replyInDm(Message message) {

    }

    @Override
    public void replyInDm(Message message, Consumer<Message> success) {

    }

    @Override
    public void replyInDm(Message message, Consumer<Message> success, Consumer<Throwable> failure) {

    }

    @Override
    public void replyInDm(String message, File file, String filename) {

    }

    @Override
    public void replySuccess(String message) {

    }

    @Override
    public void replySuccess(String message, Consumer<Message> queue) {

    }

    @Override
    public void replyWarning(String message) {

    }

    @Override
    public void replyWarning(String message, Consumer<Message> queue) {

    }

    @Override
    public void replyError(String message) {

    }

    @Override
    public void replyError(String message, Consumer<Message> queue) {

    }

    @Override
    public void reactSuccess() {

    }

    @Override
    public void reactWarning() {

    }

    @Override
    public void reactError() {

    }

    @Override
    public void async(Runnable runnable) {

    }

    @Override
    public SelfUser getSelfUser() {
        return null;
    }

    @Override
    public Member getSelfMember() {
        return null;
    }

    @Override
    public boolean isOwner() {
        return false;
    }

    @Override
    public User getAuthor() {
        return user;
    }

    @Override
    public MessageChannel getChannel() {
        return textChannel;
    }

    @Override
    public ChannelType getChannelType() {
        return channelType;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public Member getMember() {
        return member;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public PrivateChannel getPrivateChannel() {
        return privateChannel;
    }

    @Override
    public long getResponseNumber() {
        return 0;
    }

    @Override
    public TextChannel getTextChannel() {
        return textChannel;
    }

    @Override
    public boolean isFromType(ChannelType channelType) {
        return channelType == this.channelType;
    }

}
