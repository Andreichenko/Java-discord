package testUtils;

public class SeekCommandTestMocker {

    public static CommandEvent createMockCommandEventWhereBotNotConnected(ArgumentCaptor<String> stringArgumentCaptor) {
        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);
    }

}
