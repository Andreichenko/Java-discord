package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Collections;
import java.util.List;

public class ShufflesCommand extends Command{

    public ShufflesCommand() {
        this.name = "shuffle";
        this.help = "queue";
    }

    @Override
    protected void execute(CommandEvent event) {

        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {

            event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();
            return;
        }

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();

        List<AudioTrack> queue = trackScheduler.getQueue();
        Collections.shuffle(queue);
        trackScheduler.setQueue(queue);

        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();
    }
}
