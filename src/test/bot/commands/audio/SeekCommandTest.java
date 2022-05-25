package bot.commands.audio;

import bot.utils.TextChannelResponses;
import bot.utils.TimeUtils;
import bot.utils.commands.CommandEvent;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class SeekCommandTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void seekWithHoursMinutesSeconds() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        SeekCommand seekCommand = new SeekCommand();

        assertEquals(10922000, longArgumentCaptor.getValue().longValue());
        assertEquals(String.format(TextChannelResponses.SEEKING_TO_INFORMATION, TimeUtils.timeString(10922)),
                stringArgumentCaptor.getValue());
    }
}
