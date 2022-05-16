package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

import static bot.utils.EmbedUtils.createEmbedBuilder;
import static bot.utils.TextChannelResponses.CANT_DISPLAY_QUEUE_PAGE;

public class QueueCommand extends Command {
    public QueueCommand() {
        this.name = "queue";
        this.help = "See the queue for the server";
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        MessageChannel channel = event.getChannel();

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        if (audioPlayerSendHandler == null) {
            channel.sendMessage("**Queue is empty**").queue();
            return;
        }

        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();
        List<AudioTrack> queue = trackScheduler.getQueue();

        if (queue.isEmpty()) {
            channel.sendMessage("**Queue is empty**").queue();
            return;
        }
        try {
            EmbedBuilder eb = createEmbedBuilder(event, trackScheduler, queue, true);
            event.getChannel().sendMessage(eb.build()).queue();
        } catch(NumberFormatException e) {
            event.getChannel().sendMessage(CANT_DISPLAY_QUEUE_PAGE).queue();
        }
    }
}