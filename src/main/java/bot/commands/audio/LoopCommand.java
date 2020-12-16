package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.commands.audio.utils.VoiceChannel;
import bot.utils.ChannelTextResponses;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoopCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(LoopCommand.class);

    public LoopCommand() {

        this.name = "loop";
        this.help = "Enable or disable the current song from looping.";
    }

    @Override
    protected void execute(CommandEvent event) {

        AudioPlayerSendHandler audioPlayerSendHandler;
        try {
            audioPlayerSendHandler = VoiceChannel.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());

        }catch (IllegalArgumentException e){
            event.getChannel().sendMessage(ChannelTextResponses.BOT_NOT_CONNECTED_TO_VOICE).queue();
            return;
        }

        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();

        AudioTrack nowPlaying = audioPlayerSendHandler.getAudioPlayer().getPlayingTrack();
        AudioTrack loopTrack = trackScheduler.getLoopTrack();

        if (nowPlaying == null){
            event.getChannel().sendMessage(ChannelTextResponses.NOTHING_CURRENTLY_PLAYING).queue();
            return;
        }

        if (loopTrack == null){
            trackScheduler.setLoopTrack(nowPlaying);
            event.getChannel().sendMessage(ChannelTextResponses.LOOP_ENABLED).queue();
            LOGGER.info("Loop enabled");
        } else {
            trackScheduler.setLoopTrack(null);
            event.getChannel().sendMessage(ChannelTextResponses.LOOP_DISABLED).queue();
            LOGGER.info("Loop disabled");
        }
    }
}
