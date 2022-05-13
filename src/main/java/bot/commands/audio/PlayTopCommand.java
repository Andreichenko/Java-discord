package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannelUtils;
import bot.repositories.OptionEntityRepository;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class PlayTopCommand extends Command {
    private final AudioPlayerManager playerManager;
    private final String youtubeApiKey;
    private final OptionEntityRepository optionEntityRepository;

    public PlayTopCommand(AudioPlayerManager playerManager, String youtubeApiKey,
                          OptionEntityRepository optionEntityRepository) {
        this.playerManager = playerManager;
        this.name = "playtop";
        this.aliases = new String[]{"pt"};
        this.help = "Plays a song with the given name or url by placing it at the top of the queue.";

        this.youtubeApiKey = youtubeApiKey;
        this.optionEntityRepository = optionEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event) {
        VoiceChannelUtils.searchAndPlaySong(event, true, playerManager, youtubeApiKey, optionEntityRepository);
    }
}