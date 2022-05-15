package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoopCommand extends Command {
    private final Logger LOGGER = LogManager.getLogger(LoopCommand.class);

    public LoopCommand() {
        this.name = "loop";
        this.help = "Enable or disable the current song from looping.";
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioPlayerSendHandler audioPlayerSendHandler;
    }
}
