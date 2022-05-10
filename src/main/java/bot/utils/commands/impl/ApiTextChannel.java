package bot.utils.commands.impl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;
import net.dv8tion.jda.api.requests.restaction.WebhookAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class ApiTextChannel  implements TextChannel{

    @Nullable
    @Override
    public String getTopic() {
        return null;
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public boolean isNews() {
        return false;
    }

    @Override
    public int getSlowmode() {
        return 0;
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return null;
    }

    @Override
    public long getLatestMessageIdLong() {
        return 0;
    }

    @Override
    public boolean hasLatestMessage() {
        return false;
    }

    @NotNull
    @Override
    public String getName() {
        return null;
    }

    @NotNull
    @Override
    public Guild getGuild() {
        return null;
    }

    @Nullable
    @Override
    public Category getParent()
    {
        return null;
    }

    @NotNull
    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public int getPositionRaw() {
        return 0;
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Nullable
    @Override
    public PermissionOverride getPermissionOverride(@NotNull IPermissionHolder permissionHolder) {
        return null;
    }

    @NotNull
    @Override
    public List<PermissionOverride> getPermissionOverrides() {
        return null;
    }

    @NotNull
    @Override
    public List<PermissionOverride> getMemberPermissionOverrides() {
        return null;
    }

    @NotNull
    @Override
    public List<PermissionOverride> getRolePermissionOverrides() {
        return null;
    }

    @Override
    public boolean isSynced() {
        return false;
    }

    @NotNull
    @Override
    public ChannelAction<TextChannel> createCopy(@NotNull Guild guild) {
        return null;
    }

    @NotNull
    @Override
    public ChannelAction<TextChannel> createCopy() {
        return null;
    }

    @NotNull
    @Override
    public ChannelManager getManager() {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @NotNull
    @Override
    public PermissionOverrideAction createPermissionOverride(@NotNull IPermissionHolder permissionHolder) {
        return null;
    }

    @NotNull
    @Override
    public PermissionOverrideAction putPermissionOverride(@NotNull IPermissionHolder permissionHolder) {
        return null;
    }

    @NotNull
    @Override
    public InviteAction createInvite() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<List<Invite>> retrieveInvites() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<List<Webhook>> retrieveWebhooks() {
        return null;
    }

    @NotNull
    @Override
    public WebhookAction createWebhook(@NotNull String name) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Webhook.WebhookReference> follow(@NotNull String targetChannelId) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> deleteMessages(@NotNull Collection<Message> messages) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> deleteMessagesByIds(@NotNull Collection<String> messageIds) {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> deleteWebhookById(@NotNull String id) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactionsById(@NotNull String messageId) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactionsById(@NotNull String messageId, @NotNull String unicode) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactionsById(@NotNull String messageId, @NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReactionById(@NotNull String messageId, @NotNull String unicode, @NotNull User user) {
        return null;
    }

    @Override
    public boolean canTalk() {
        return false;
    }

    @Override
    public boolean canTalk(@NotNull Member member) {
        return false;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return 0;
    }

    @NotNull
    @Override
    public String getAsMention() {
        return null;
    }

    @Override
    public long getIdLong() {
        return 0;
    }

    @NotNull
    @Override
    public MessageAction sendMessage(@NotNull CharSequence text) {
        return new BlankMessageAction();
    }

    @NotNull
    @Override
    public MessageAction sendMessageFormat(@NotNull String format, @NotNull Object... args) {
        return new BlankMessageAction();
    }

    @NotNull
    @Override
    public MessageAction sendMessage(@NotNull MessageEmbed embed) {
        return new BlankMessageAction();
    }

    @NotNull
    @Override
    public MessageAction sendMessage(@NotNull Message msg) {
        return new BlankMessageAction();
    }

    @NotNull
    @Override
    public RestAction<Void> sendTyping() {
        return new BlankRestAction<>();
    }

}
