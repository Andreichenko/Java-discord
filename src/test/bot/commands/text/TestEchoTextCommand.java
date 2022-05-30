package bot.commands.text;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TestEchoTextCommand {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void testFailsWhenNoArgsAreSent(){

    }

}
