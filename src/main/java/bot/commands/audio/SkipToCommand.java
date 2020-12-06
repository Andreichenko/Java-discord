package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.List;

public class SkipToCommand extends Command{

    /**
     * description
     */

    public SkipToCommand(){

        this.name = "skipto";
        this.help = "Skips to a certain position in the queue.";
    }

    @Override
    protected void execute(CommandEvent event) {

        AudioPlayerSendHandler audioPlayerSendHandler;

        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();
        int elementSkipTo;
        List<AudioTrack> queue = trackScheduler.getQueue();
        List<AudioTrack> sublistQueue = queue.subList(elementSkipTo - 1, queue.size());

        trackScheduler.setQueue(sublistQueue);
        audioPlayerSendHandler.getAudioPlayer().stopTrack();
        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();
    }
}
