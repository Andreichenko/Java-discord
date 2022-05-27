package bot.utils;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockTextChannelCreator {

    public static TextChannel createMockTextChannelWhereTextIsSentNoTyping(ArgumentCaptor<String> stringArgumentCaptor){
        MessageAction mockMessageAction = mock(MessageAction.class);
        doAnswer(invocation -> null).when(mockMessageAction).queue();

        TextChannel mockTextChannel = mock(TextChannel.class);
        when(mockTextChannel.sendMessage(stringArgumentCaptor.capture())).thenReturn(mockMessageAction);

        return mockTextChannel;
    }
}
