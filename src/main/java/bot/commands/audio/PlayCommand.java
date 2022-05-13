package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannelUtils;
import bot.repositories.OptionEntityRepository;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger(PlayCommand.class);
    private final AudioPlayerManager playerManager;
    private final String youtubeApiKey;
    private final OptionEntityRepository optionEntityRepository;

    public PlayCommand(AudioPlayerManager playerManager, String youtubeApiKey, OptionEntityRepository optionEntityRepository) {
        this.playerManager = playerManager;
        this.name = "play";
        this.help = "Plays a song with the given name or url.";

        this.youtubeApiKey = youtubeApiKey;
        this.optionEntityRepository = optionEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event) {
        LOGGER.info("Play command triggered with message {}", event.getArgs());
        VoiceChannelUtils.searchAndPlaySong(event, false, playerManager, youtubeApiKey, optionEntityRepository);
    }
}