package bot.utils.commands.impl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageReference;
import net.dv8tion.jda.api.entities.MessageSticker;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.apache.commons.collections4.Bag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.List;

public class ApiMessage implements Message {
    String rawContent;

    public ApiMessage(String rawContent) {
        this.rawContent = rawContent;
    }

    @Nullable
    @Override
    public MessageReference getMessageReference() {
        return null;
    }

    @Nullable
    @Override
    public Message getReferencedMessage() {
        return null;
    }

    @NotNull
    @Override
    public List<User> getMentionedUsers() {
        return null;
    }

    @NotNull
    @Override
    public Bag<User> getMentionedUsersBag() {
        return null;
    }

    @NotNull
    @Override
    public List<TextChannel> getMentionedChannels() {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Bag<TextChannel> getMentionedChannelsBag() {
        return null;
    }

    @NotNull
    @Override
    public List<Role> getMentionedRoles() {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Bag<Role> getMentionedRolesBag() {
        return null;
    }

    @NotNull
    @Override
    public List<Member> getMentionedMembers(@NotNull Guild guild) {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<Member> getMentionedMembers() {
        return null;
    }

    @NotNull
    @Override
    public List<IMentionable> getMentions(@NotNull MentionType... types) {
        return null;
    }

    @Override
    public boolean isMentioned(@NotNull IMentionable mentionable, @NotNull MentionType... types) {
        return false;
    }

    @Override
    public boolean mentionsEveryone() {
        return false;
    }

    @Override
    public boolean isEdited() {
        return false;
    }

    @Nullable
    @Override
    public OffsetDateTime getTimeEdited() {
        return null;
    }

    @NotNull
    @Override
    public User getAuthor() {
        return null;
    }

    @Nullable
    @Override
    public Member getMember() {
        return null;
    }

    @NotNull
    @Override
    public String getJumpUrl() {
        return null;
    }

    @NotNull
    @Override
    public String getContentDisplay() {
        return null;
    }

    @NotNull
    @Override
    public String getContentRaw() {
        return rawContent;
    }

    @NotNull
    @Override
    public String getContentStripped() {
        return null;
    }

    @NotNull
    @Override
    public List<String> getInvites() {
        return null;
    }

    @Nullable
    @Override
    public String getNonce() {
        return null;
    }

    @Override
    public boolean isFromType(@NotNull ChannelType type) {
        return false;
    }

    @NotNull
    @Override
    public ChannelType getChannelType() {
        return null;
    }

    @Override
    public boolean isWebhookMessage() {
        return false;
    }

    @NotNull
    @Override
    public MessageChannel getChannel() {
        return null;
    }

    @NotNull
    @Override
    public PrivateChannel getPrivateChannel() {
        return null;
    }

    @NotNull
    @Override
    public TextChannel getTextChannel() {
        return null;
    }

    @Nullable
    @Override
    public Category getCategory() {
        return null;
    }

    @NotNull
    @Override
    public Guild getGuild() {
        return null;
    }

    @NotNull
    @Override
    public List<Attachment> getAttachments() {
        return null;
    }

    @NotNull
    @Override
    public List<MessageEmbed> getEmbeds() {
        return null;
    }

    @NotNull
    @Override
    public List<ActionRow> getActionRows() {
        return null;
    }

    @NotNull
    @Override
    public List<Emote> getEmotes() {
        return null;
    }

    @NotNull
    @Override
    public Bag<Emote> getEmotesBag() {
        return null;
    }

    @NotNull
    @Override
    public List<MessageReaction> getReactions() {
        return null;
    }

    @NotNull
    @Override
    public List<MessageSticker> getStickers() {
        return null;
    }

    @Override
    public boolean isTTS() {
        return false;
    }

    @Nullable
    @Override
    public MessageActivity getActivity() {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull CharSequence newContent) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull MessageEmbed newContent) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageEmbeds(@NotNull Collection<? extends MessageEmbed> embeds) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageComponents(@NotNull Collection<? extends ComponentLayout> components) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageFormat(@NotNull String format, @NotNull Object... args) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull Message newContent) {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public boolean isPinned() {
        return false;
    }

    @NotNull
    @Override
    public RestAction<Void> pin() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> unpin() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull Emote emote) {
        return new BlankRestAction<>();
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull String unicode) {
        return new BlankRestAction<>();
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull String unicode) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote, @NotNull User user) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String unicode) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String unicode, @NotNull User user) {
        return null;
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull String unicode) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@NotNull String unicode) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(@NotNull String id) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(long id) {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> suppressEmbeds(boolean suppressed) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Message> crosspost() {
        return null;
    }

    @Override
    public boolean isSuppressedEmbeds() {
        return false;
    }

    @NotNull
    @Override
    public EnumSet<MessageFlag> getFlags(){
        return null;
    }

    @Override
    public long getFlagsRaw() {
        return 0;
    }

    @Override
    public boolean isEphemeral() {
        return false;
    }

    @NotNull
    @Override
    public MessageType getType() {
        return null;
    }

    @Nullable
    @Override
    public Interaction getInteraction() {
        return null;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {

    }

    @Override
    public long getIdLong() {
        return 0;
    }
}