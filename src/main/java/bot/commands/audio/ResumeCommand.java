package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannelUtils;
import bot.utils.TextChannelResponses;
import bot.utils.UnicodeEmote;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeCommand extends Command {
    private final Logger LOGGER = LogManager.getLogger(ResumeCommand.class);

    public ResumeCommand() {
        this.name = "resume";
        this.help = "Resume the song if it's been paused.";
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            VoiceChannelUtils.setPauseStatusOnAudioPlayer(event, false);
        } catch(IllegalArgumentException e) {
            LOGGER.debug("Trying to resume a currently playing song", e);
            event.getChannel().sendMessage(TextChannelResponses.TRYING_TO_RESUME_PLAYING_SONG).queue();
            return;
        } catch(IllegalAccessException e) {
            LOGGER.debug("Error while running resume command", e);
            return;
        }
        event.getMessage().addReaction(UnicodeEmote.THUMBS_UP).queue();
    }
}