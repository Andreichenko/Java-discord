package bot.utils.commands.impl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class BlankMessageAction implements MessageAction{

    @NotNull
    @Override
    public JDA getJDA() {
        return null;
    }

    @NotNull
    @Override
    public MessageAction setCheck(@Nullable BooleanSupplier checks) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction timeout(long timeout, @NotNull TimeUnit unit) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction deadline(long timestamp) {
        return null;
    }

    @Override
    public void queue(@Nullable Consumer<? super Message> success, @Nullable Consumer<? super Throwable> failure) {

    }

    @Override
    public Message complete(boolean shouldQueue) throws RateLimitedException {
        return null;
    }

    @NotNull
    @Override
    public CompletableFuture<Message> submit(boolean shouldQueue) {
        return null;
    }

    @NotNull
    @Override
    public MessageChannel getChannel() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isEdit() {
        return false;
    }

    @NotNull
    @Override
    public MessageAction apply(@Nullable Message message) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction referenceById(long messageId) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction mentionRepliedUser(boolean mention) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction failOnInvalidReply(boolean fail) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction tts(boolean isTTS) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction reset() {
        return null;
    }

    @NotNull
    @Override
    public MessageAction nonce(@Nullable String nonce) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction content(@Nullable String content) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction embed(@Nullable MessageEmbed embed) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction setEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction append(@Nullable CharSequence csq, int start, int end) {
        return this;
    }

    @NotNull
    @Override
    public MessageAction append(char c) {
        return this;
    }

    @NotNull
    @Override
    public MessageAction addFile(@NotNull InputStream data, @NotNull String name, @NotNull AttachmentOption... options) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction addFile(@NotNull File file, @NotNull String name, @NotNull AttachmentOption... options) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction clearFiles() {
        return null;
    }

    @NotNull
    @Override
    public MessageAction clearFiles(@NotNull BiConsumer<String, InputStream> finalizer) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction clearFiles(@NotNull Consumer<InputStream> finalizer) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction retainFilesById(@NotNull Collection<String> ids) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction setActionRows(@NotNull ActionRow... rows) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction override(boolean bool) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction allowedMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction mention(@NotNull IMentionable... mentions) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction mentionUsers(@NotNull String... userIds) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction mentionRoles(@NotNull String... roleIds) {
        return null;
    }
}
