package testUtils;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AudioTestMocker {


    public static CommandEvent createMockCommandEventForPlayCommandWhereItErrorsOut(String textChannelId,
                                                                                    String memberId,
                                                                                    String guildId,
                                                                                    String commandArgument){

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        Member mockMember = mock(Member.class);
        when(mockMember.getId()).thenReturn(memberId);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getTextChannelById(anyString())).thenReturn(mockTextChannel);
        when(mockGuild.getMemberById(anyString())).thenReturn(mockMember);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;

    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereGuildCantBeFound(String textChannelId,
                                                                                         String memberId,
                                                                                         String guildId,
                                                                                         String commandArgument){

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        Member mockMember = mock(Member.class);
        when(mockMember.getId()).thenReturn(memberId);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(null);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getId()).thenReturn(guildId);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;
    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereMemberCantBeFound(String textChannelId,
                                                                                          String memberId,
                                                                                          String guildId,
                                                                                          String commandArgument){
        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        Member mockMember = mock(Member.class);
        when(mockMember.getId()).thenReturn(memberId);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getTextChannelById(any())).thenReturn(mockTextChannel);
        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getMemberById(any())).thenReturn(null);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;

    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereItErrorsOut(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                    String textChannelId,
                                                                                    String memberId,
                                                                                    String guildId,
                                                                                    String commandArgument,
                                                                                    Answer<Void> messageQueuedAnswer){
        CommandEvent mockCommandEvent = createMockCommandEventForPlayCommandWhereItErrorsOut(
                textChannelId, memberId, guildId, commandArgument);

        TextChannel mockTextChannel = (TextChannel) mockCommandEvent.getChannel();

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(messageQueuedAnswer).when(mockMessageAction).queue();

        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        return mockCommandEvent;
    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereBotLacksPermissionToJoinVoiceChannel(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                                             String textChannelId,
                                                                                                             String memberId,
                                                                                                             String guildId,
                                                                                                             String mockVoiceChannelId,
                                                                                                             String commandArgument,
                                                                                                             AudioSendHandler audioPlayerSendHandler){

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();


        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        Member mockMember = mock(Member.class);
        Guild mockGuild = mock(Guild.class);

        GuildVoiceState mockGuildVoiceState = mock(GuildVoiceState.class);
        when(mockGuildVoiceState.inVoiceChannel()).thenReturn(true);

        when(mockMember.getId()).thenReturn(memberId);
        when(mockMember.getVoiceState()).thenReturn(mockGuildVoiceState);

        AudioManager mockAudioManager = mock(AudioManager.class);
        when(mockAudioManager.isConnected()).thenReturn(false);
        doAnswer(invocation ->
        {
            throw new InsufficientPermissionException(mockGuild, Permission.ADMINISTRATOR);
        }).when(mockAudioManager).openAudioConnection(any());

        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getTextChannelById(anyString())).thenReturn(mockTextChannel);
        when(mockGuild.getMemberById(anyString())).thenReturn(mockMember);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;
    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereVoiceChannelNeedsToBeJoinedAudioGetsPlayed(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                                                   String textChannelId,
                                                                                                                   String memberId,
                                                                                                                   String guildId,
                                                                                                                   String mockVoiceChannelId,
                                                                                                                   String commandArgument,
                                                                                                                   AudioSendHandler audioPlayerSendHandler,
                                                                                                                   ArgumentCaptor<VoiceChannel> voiceChannelArgumentCaptor) {

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> mockMessageAction).when(mockMessageAction).append(stringArgumentCaptor.capture());
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        RestAction mockRestAction = mock(RestAction.class);

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
        when(mockTextChannel.getId()).thenReturn(textChannelId);
        when(mockTextChannel.sendTyping()).thenReturn(mockRestAction);

        Member mockMember = mock(Member.class);

        VoiceChannel mockVoiceChannel = mock(VoiceChannel.class);
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(mockMember);
        when(mockVoiceChannel.getMembers()).thenReturn(memberList);
        when(mockVoiceChannel.getId()).thenReturn(mockVoiceChannelId);

        GuildVoiceState mockGuildVoiceState = mock(GuildVoiceState.class);
        when(mockGuildVoiceState.inVoiceChannel()).thenReturn(true);
        when(mockGuildVoiceState.getChannel()).thenReturn(mockVoiceChannel);

        when(mockMember.getId()).thenReturn(memberId);
        when(mockMember.getVoiceState()).thenReturn(mockGuildVoiceState);

        AudioManager mockAudioManager = mock(AudioManager.class);
        when(mockAudioManager.isConnected()).thenReturn(false);
        when(mockAudioManager.getConnectedChannel()).thenReturn(mockVoiceChannel);
        when(mockAudioManager.getSendingHandler()).thenReturn(audioPlayerSendHandler);
        doAnswer(invocation -> null).when(mockAudioManager).openAudioConnection(voiceChannelArgumentCaptor.capture());

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getTextChannelById(anyString())).thenReturn(mockTextChannel);
        when(mockGuild.getMemberById(anyString())).thenReturn(mockMember);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;
    }

    public static CommandEvent createMockCommandEventForPlayCommandWhereAudioGetsPlayed(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                        String textChannelId,
                                                                                        String memberId,
                                                                                        String guildId,
                                                                                        String commandArgument,
                                                                                        boolean isVoiceConnected,
                                                                                        AudioSendHandler audioPlayerSendHandler,
                                                                                        ArgumentCaptor<MessageEmbed> messageEmbedArgumentCaptor){

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> mockMessageAction).when(mockMessageAction).append(stringArgumentCaptor.capture());
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        RestAction mockRestAction = mock(RestAction.class);

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
//        when(mockTextChannel.sendMessage(messageEmbedArgumentCaptor.capture())).thenReturn(mockMessageAction);
        when(mockTextChannel.getId()).thenReturn(textChannelId);
        when(mockTextChannel.sendTyping()).thenReturn(mockRestAction);

        Member mockMember = mock(Member.class);
        when(mockMember.getId()).thenReturn(memberId);

        VoiceChannel mockVoiceChannel = mock(VoiceChannel.class);
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(mockMember);
        when(mockVoiceChannel.getMembers()).thenReturn(memberList);

        AudioManager mockAudioManager = mock(AudioManager.class);
        when(mockAudioManager.isConnected()).thenReturn(isVoiceConnected);
        when(mockAudioManager.getConnectedChannel()).thenReturn(mockVoiceChannel);
        when(mockAudioManager.getSendingHandler()).thenReturn(audioPlayerSendHandler);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getTextChannelById(anyString())).thenReturn(mockTextChannel);
        when(mockGuild.getMemberById(anyString())).thenReturn(mockMember);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;
    }


    public static CommandEvent createMockCommandEventForPlayCommandWhereMemberNotInVoiceChannel(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                                String textChannelId,
                                                                                                String memberId,
                                                                                                String guildId,
                                                                                                String commandArgument){

        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
        when(mockTextChannel.getId()).thenReturn(textChannelId);

        Member mockMember = mock(Member.class);

        GuildVoiceState mockGuildVoiceState = mock(GuildVoiceState.class);
        when(mockGuildVoiceState.inVoiceChannel()).thenReturn(false);

        when(mockMember.getId()).thenReturn(memberId);
        when(mockMember.getVoiceState()).thenReturn(mockGuildVoiceState);

        AudioManager mockAudioManager = mock(AudioManager.class);
        when(mockAudioManager.isConnected()).thenReturn(false);

        Guild mockGuild = mock(Guild.class);
        when(mockGuild.getId()).thenReturn(guildId);
        when(mockGuild.getTextChannelById(anyString())).thenReturn(mockTextChannel);
        when(mockGuild.getMemberById(anyString())).thenReturn(mockMember);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);

        JDA mockJDA = mock(JDA.class);
        when(mockJDA.getGuildById(anyString())).thenReturn(mockGuild);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getJDA()).thenReturn(mockJDA);
        when(mockCommandEvent.getArgs()).thenReturn(commandArgument);
        when(mockCommandEvent.getChannel()).thenReturn(mockTextChannel);
        when(mockCommandEvent.getGuild()).thenReturn(mockGuild);
        when(mockCommandEvent.getMember()).thenReturn(mockMember);

        return mockCommandEvent;
    }
}
