package bot.utils;

import bot.commands.audio.utils.TrackSchedulers;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.List;
import java.util.Random;


/**
 * get a random colour for the embed
 * Java 'Color' class takes 3 floats, from 0 to 1.
 */
public class EmBuilder {

    public static EmbedBuilder createEmbedBuilder( boolean totalTime, TrackSchedulers trackSchedulers, List<AudioTrack> queue,
                                                   CommandEvent event){

        return null;
    }

    Random rand = new Random();

    private static Integer getPageNumber(CommandEvent event, double totalPages){

        int page = 1;

        return null;
    }
}
