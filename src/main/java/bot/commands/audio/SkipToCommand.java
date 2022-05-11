package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.commands.audio.utils.VoiceChannelUtils;
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

        try {

            audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());

        } catch(IllegalArgumentException e) {

            event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();

            return;
        }

        int elementSkipTo;

        try {

            elementSkipTo = Integer.parseInt(event.getArgs());

        } catch(NumberFormatException e) {

            event.getChannel().sendMessage("**You need to provide a number to skip to.**").queue();

            return;
        }

        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();

        List<AudioTrack> queue = trackScheduler.getQueue();
        List<AudioTrack> sublistQueue = queue.subList(elementSkipTo - 1, queue.size());

        trackScheduler.setQueue(sublistQueue);
        audioPlayerSendHandler.getAudioPlayer().stopTrack();
        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();
    }
}
