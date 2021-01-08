package bot.commands.audio;

import com.jagrosh.jdautilities.command.CommandEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

public class SeekCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void seekWithHoursMinutesSeconds(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    }
}
