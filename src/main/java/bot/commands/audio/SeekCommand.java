package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.ChannelTextResponses;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.managers.AudioManager;

public class SeekCommand extends Command {


    public SeekCommand() {
        this.name = "seek";
        this.help = "Seeks to a certain point in the current track.";
    }

    @Override
    protected void execute(CommandEvent event) {


        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()){

            event.getChannel().sendMessage(ChannelTextResponses.BOT_NOT_CONNECTED_TO_VOICE).queue();
            return;
        }
        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        AudioTrack audioTrack = audioPlayerSendHandler.getAudioPlayer().getPlayingTrack();

        String seekPoint = event.getArgs();
        int seekTime;

        try {
            seekTime = getSeekTime(seekPoint);
        } catch(IllegalArgumentException e){
            event.getChannel().sendMessage(ChannelTextResponses.SEEK_COMMAND_FORMAT).queue();
            return;
        }

        if (seekTime * 1000 > audioTrack.getDuration() || !audioTrack.isSeekable()){
            event.getChannel().sendMessage(ChannelTextResponses.SEEK_POINT_LONGER_THAN_SONG).queue();
            return;
        }
    }

    private int getSeekTime(String seekPoint) {
//
//        int seekTime;
//        return seekTime;

        return 0;
    }
}
