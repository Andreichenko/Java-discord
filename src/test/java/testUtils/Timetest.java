package testUtils;

import bot.utils.TimeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Timetest {


    @Test
    public void TestTimeUtilsWithHoursMinsSeconds()
    {
        // This is 1 hour 1 minute and 40 seconds
        String timeString = TimeUtils.timeString(3700);
        assertEquals("1:01:40", timeString);
    }
}
