package bot.commands.audio;

import bot.commands.text.TextCommand;
import bot.utils.ChannelTextResponses;
import bot.utils.TimeLineStamp;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.TextChannel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import testUtils.SeekCommandTestMocker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testUtils.MockTextChannelCreator.createMockTextChannelWhereTextIsSentNoTyping;

public class SeekCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void seekWithHoursMinutesSeconds(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
                longArgumentCaptor, "3:02:02");

        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);

        assertEquals(10922000, longArgumentCaptor.getValue().longValue());
        assertEquals(String.format(ChannelTextResponses.SEEKING_TO_INFORMATION, TimeLineStamp.timeString(10922)),
                stringArgumentCaptor.getValue());
    }

    @Test
    public void seekWithMinutesSeconds(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
                longArgumentCaptor, "02:02");

        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);

        assertEquals(122000, longArgumentCaptor.getValue().longValue());
        assertEquals(String.format(ChannelTextResponses.SEEKING_TO_INFORMATION, TimeLineStamp.timeString(122)),
                stringArgumentCaptor.getValue());

    }

    @Test
    public void seekWithSeconds(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventWithTime(stringArgumentCaptor,
                longArgumentCaptor, "02");

        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);

        assertEquals(2000, longArgumentCaptor.getValue().longValue());
        assertEquals(String.format(ChannelTextResponses.SEEKING_TO_INFORMATION, TimeLineStamp.timeString(2)),
                stringArgumentCaptor.getValue());
    }

    @Test
    public void seekWithInvalidFormatFails(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventThatFailsWithTime(stringArgumentCaptor,
                "3:4:5:6");
        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);
        assertEquals(ChannelTextResponses.SEEK_COMMAND_FORMAT, stringArgumentCaptor.getValue());
    }

    @Test
    public void seekWithStringFails(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        CommandEvent mockCommandEvent = SeekCommandTestMocker.createMockCommandEventThatFailsWithTime(stringArgumentCaptor,
                "string");
        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);
        assertEquals(ChannelTextResponses.SEEK_COMMAND_FORMAT, stringArgumentCaptor.getValue());
    }

    @Test
    public void seekFailsWhereSeekPointLongerThanSong(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent =
                SeekCommandTestMocker.createMockCommandEventThatFailsSongTooLong(stringArgumentCaptor,
                        "32:45");
        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);
        assertEquals(ChannelTextResponses.SEEK_POINT_LONGER_THAN_SONG, stringArgumentCaptor.getValue());

    }

    @Test
    public void seekFailsWhereSongNotSeekable(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        CommandEvent mockCommandEvent =
                SeekCommandTestMocker.createMockCommandEventThatFailsSongNotSeekable(stringArgumentCaptor,
                        "33:22");

        SeekCommand seekCommand = new SeekCommand();
        seekCommand.execute(mockCommandEvent);

        assertEquals(ChannelTextResponses.SEEK_POINT_LONGER_THAN_SONG, stringArgumentCaptor.getValue());

    }


}
