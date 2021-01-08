package bot.commands.audio;

import bot.utils.ChannelTextResponses;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import testUtils.SeekCommandTestMocker;

import static org.junit.Assert.assertEquals;

public class SeekCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void seekWithHoursMinutesSeconds(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
                longArgumentCaptor, "3:04:04");
        //need specific class

        //TODO need to create a class for seekcommands, it's necessary class for notice in the chat

    }
}
