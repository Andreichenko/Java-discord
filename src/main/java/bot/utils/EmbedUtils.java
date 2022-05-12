package bot.utils;

import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.commands.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EmbedUtils {
    public static EmbedBuilder createEmbedBuilder(CommandEvent event, TrackSchedulers trackScheduler,
                                                  List<AudioTrack> queue, boolean totalTime) throws NumberFormatException {
        double totalPages = Math.ceil((double) queue.size() / 10);
        Integer page = getPageNumber(event, totalPages);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setFooter(String.format("Page %d/%.0f", page, totalPages), event.getAuthor().getAvatarUrl());

        //get a random colour for the embed
        setRandomColour(eb);

        AtomicInteger ordinal = new AtomicInteger(1);
        StringBuilder sb = new StringBuilder();

        for (AudioTrack audioTrack : queue) {
            int itemPosition = ordinal.getAndIncrement();
            if (itemPosition < 10 * page + 1 - 10) continue;

            sb.append(String.format("`%d.` [%s](%s) | %s\n\n", itemPosition, audioTrack.getInfo().title,
                    audioTrack.getInfo().uri, TimeUtils.timeString(audioTrack.getDuration() / 1000)));

            if (ordinal.get() > 10 * page) break;
        }

        if (totalTime) {
            sb.append(String.format("%d songs in queue | %s total duration", queue.size(),
                    TimeUtils.timeString(trackScheduler.getQueueDurationInMilliSeconds() / 1000)));
        } else {
            sb.append(String.format("%d songs in history", queue.size()));
        }

        eb.setDescription(sb);
        return eb;
    }

    private static Integer getPageNumber(CommandEvent event, double totalPages) {
        int page = 1;

        if (!event.getArgs().equals("")) {
            page = Integer.parseInt(event.getArgs());

            if (page > totalPages)
            {
                throw new NumberFormatException();
            }
        }
        return page;
    }

    public static void splitTextListsToSend(List<String> eachAliasDescription, MessageChannel channel) {
        ArrayList<String> fullMessagesToSend = new ArrayList<>();

        int index = -1;
        for (String aliasDescription : eachAliasDescription) {
            if (fullMessagesToSend.isEmpty()) {
                fullMessagesToSend.add(aliasDescription);
                index++;
            } else {
                String previousMessage = fullMessagesToSend.get(index);

                if (aliasDescription.length() + previousMessage.length() < 2000) {
                    fullMessagesToSend.remove(index);
                    aliasDescription = previousMessage + aliasDescription;
                    fullMessagesToSend.add(aliasDescription);
                } else {
                    fullMessagesToSend.add(aliasDescription);
                    index++;
                }
            }
        }

        fullMessagesToSend.forEach(message -> channel.sendMessage(message).queue());
    }

    public static void setRandomColour(EmbedBuilder eb) {
        Random rand = new Random();
        // Java 'Color' class takes 3 floats, from 0 to 1.
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        eb.setColor(randomColor);
    }
}