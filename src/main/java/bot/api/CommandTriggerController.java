package bot.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/command")
@RestController
public class CommandTriggerController {
    private static final Logger LOGGER = LogManager.getLogger(CommandTriggerController.class);
}
