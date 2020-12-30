package testUtils;

import com.jagrosh.jdautilities.command.CommandEvent;

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

    public static CommandEvent createMockCommandEventForPlayCommandWhereVoiceChannelNeedsToBeJoinedAudioGetsPlayed(ArgumentCaptor<String> stringArgumentCaptor,
                                                                                                                   String textChannelId,
                                                                                                                   String memberId,
                                                                                                                   String guildId,
                                                                                                                   String mockVoiceChannelId,
                                                                                                                   String commandArgument,
                                                                                                                   AudioSendHandler audioPlayerSendHandler,
                                                                                                                   ArgumentCaptor<VoiceChannel> voiceChannelArgumentCaptor) {

    }]
}
