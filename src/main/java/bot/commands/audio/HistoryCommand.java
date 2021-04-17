package bot.commands.audio;


import bot.commands.audio.utils.AudioPlayerSendHandler;
import com.google.common.collect.EvictingQueue;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;

import static bot.utils.TextChannelResponses.CANT_DISPLAY_QUEUE_PAGE;
import static bot.utils.TextChannelResponses.NO_HISTORY_TO_SHOW;
import static bot.utils.EmbedUtils.createEmbedBuilder;

/**
 * Add history command
 */
public class HistoryCommand extends Command{

    public HistoryCommand(){

        this.name = "history";
        this.help = "Show the play history";
    }


    @Override
    protected void execute(CommandEvent event) {

        AudioManager audioManager = event.getGuild().getAudioManager();

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();

        if (audioPlayerSendHandler == null){
            return;
        }

        EvictingQueue<AudioTrack> history = audioPlayerSendHandler.getTrackScheduler().getHistory();
        if (history.size() == 0) {
            event.getChannel().sendMessage(NO_HISTORY_TO_SHOW).queue();
            return;
        }

        try{

            EmbedBuilder emBuilder = createEmbedBuilder(event, null, new ArrayList<>(history), false);

            event.getChannel().sendMessage(emBuilder.build()).queue();

        } catch(NumberFormatException e) {

            event.getChannel().sendMessage(CANT_DISPLAY_QUEUE_PAGE).queue();
        }

    }
}
