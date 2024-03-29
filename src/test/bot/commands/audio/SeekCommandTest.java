package bot.commands.audio;

//import bot.utils.TextChannelResponses;
//import bot.utils.TimeUtils;
//import bot.utils.commands.CommandEvent;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.MockitoAnnotations;
//import testUtils.SeekCommandTestMocker;
//
//import static org.junit.Assert.assertEquals;

public class SeekCommandTest {

//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void seekWithHoursMinutesSeconds() {
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
//
//        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
//                longArgumentCaptor, "3:02:02");
//        SeekCommand seekCommand = new SeekCommand();
//        seekCommand.execute(mockCommandEvent);
//
//        assertEquals(10922000, longArgumentCaptor.getValue().longValue());
//        assertEquals(String.format(TextChannelResponses.SEEKING_TO_INFORMATION, TimeUtils.timeString(10922)),
//                stringArgumentCaptor.getValue());
//    }
//
//    @Test
//    public void seekWithMinutesSeconds(){
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
//        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
//                longArgumentCaptor, "02:02");
//
//        SeekCommand seekCommand = new SeekCommand();
//        seekCommand.execute(mockCommandEvent);
//
//        assertEquals(122000, longArgumentCaptor.getValue().longValue());
//        assertEquals(String.format(TextChannelResponses.SEEKING_TO_INFORMATION, TimeUtils.timeString(122)),
//                stringArgumentCaptor.getValue());
//
//    }
//
//    @Test
//    public void seekWithSeconds(){
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
//
//        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
//                longArgumentCaptor, "2221");
//
//        SeekCommand seekCommand = new SeekCommand();
//        seekCommand.execute(mockCommandEvent);
//
//        assertEquals(0, longArgumentCaptor.getValue().longValue());
//        assertEquals(String.format(TextChannelResponses.SEEKING_TO_INFORMATION, TimeUtils.timeString(2)),
//                stringArgumentCaptor.getValue());
//    }
//
//
//
//
//
}