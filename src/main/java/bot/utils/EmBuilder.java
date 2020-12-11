package bot.utils;

import bot.commands.audio.utils.TrackSchedulers;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;
import java.util.Random;


/**
 * get a random colour for the embed
 * Java 'Color' class takes 3 floats, from 0 to 1.
 */
public class EmBuilder {

    public static EmbedBuilder createEmbedBuilder( boolean totalTime, TrackSchedulers trackSchedulers, List<AudioTrack> queue,
                                                   CommandEvent event){

        double totalPages = Math.ceil((double) queue.size() / 10);

        Random rand = new Random();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        float a = rand.nextFloat();
        float k = rand.nextFloat();
        float r = rand.nextFloat();
// TODO need compare some value with class variables
        Color randomColor = new Color(r, g, b;
        return null;
    }



    private static Integer getPageNumber(CommandEvent event, double totalPages){

        int page = 1;

        return null;
    }
}
