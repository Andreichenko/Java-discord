package bot.commands.audio;

import bot.commands.audio.utils.VoiceChannel;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class PlayTopCommand extends Command {

    private final AudioPlayerManager playerManager;

    public PlayTopCommand(AudioPlayerManager playerManager)
    {
        this.playerManager = playerManager;
        this.name = "playtop";
        this.aliases = new String[]{"pt"};
        this.help = "Plays a song with the given name or url by placing it at the top of the queue.";
    }

    @Override
    protected void execute(CommandEvent event)
    {
        VoiceChannel.SearchAndPlaySong(event.getJDA(), event.getArgs(), event.getGuild().getId(), event.getChannel().getId(), event.getMember().getId(), true, playerManager);
    }
}
