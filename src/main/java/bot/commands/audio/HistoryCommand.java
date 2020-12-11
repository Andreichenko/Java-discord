package bot.commands.audio;


import bot.commands.audio.utils.AudioPlayerSendHandler;
import com.google.common.collect.EvictingQueue;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;

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

        if (history.size() == 0){
            return;
        }

       //?????

    }
}
