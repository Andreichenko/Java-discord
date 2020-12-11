package bot.utils;

import bot.commands.audio.utils.TrackSchedulers;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * get a random colour for the embed
 * Java 'Color' class takes 3 floats, from 0 to 1.
 */
public class EmBuilder {

    public static EmbedBuilder createEmbedBuilder(CommandEvent event, TrackSchedulers trackSchedulers,
                                                  List<AudioTrack> queue, boolean totalTime) throws NumberFormatException{

        double totalPages = Math.ceil((double) queue.size() / 10);
        Integer page = getPageNumber(event, totalPages);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setFooter(String.format("Page %d/%.0f", page, totalPages), event.getAuthor().getAvatarUrl());

        Random rand = new Random();


        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        Color randomColor = new Color(r, g, b);

        embedBuilder.setColor(randomColor);
        AtomicInteger ordinal = new AtomicInteger(1);
        StringBuilder stringBuilder = new StringBuilder();

        for (AudioTrack audioTrack : queue){
            int itemPosition = ordinal.getAndIncrement();
            if (itemPosition < 10 * page + 1 - 10) continue;

            stringBuilder.append(String.format("`%d.` [%s](%s) | %s\n\n", itemPosition, audioTrack.getInfo().title,
                    audioTrack.getInfo().uri, TimeLineStamp.timeString(audioTrack.getDuration() / 1000)));

            if (ordinal.get() > 10 * page) break;
        }
        if (totalTime){
            stringBuilder.append(String.format("%d songs in queue | %s total duration", queue.size(),
                    TimeLineStamp.timeString(trackSchedulers.getDurationInMilliSeconds() / 1000)));
        }else {
            stringBuilder.append(String.format("%d songs in history", queue.size()));
        }
        embedBuilder.setDescription(stringBuilder);
        return embedBuilder;
    }



    private static Integer getPageNumber(CommandEvent event, double totalPages){

        int page = 1;

        if (!event.getArgs().equals("")) {
            page = Integer.parseInt(event.getArgs());

            if (page > totalPages) {
                throw new NumberFormatException();
            }
        }

        return page;
    }
}
