package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.TextChannelResponses;
import bot.utils.TimeUtils;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
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

        if (!audioManager.isConnected()) {
            event.getChannel().sendMessage(TextChannelResponses.BOT_NOT_CONNECTED_TO_VOICE).queue();
            return;
        }

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        AudioTrack np = audioPlayerSendHandler.getAudioPlayer().getPlayingTrack();

        String seekPoint = event.getArgs();

        int seekTime;

        try {
            seekTime = getSeekTime(seekPoint);
        } catch(IllegalArgumentException e) {
            event.getChannel().sendMessage(TextChannelResponses.SEEK_COMMAND_FORMAT).queue();
            return;
        }

        if (seekTime * 1000 > np.getDuration() || !np.isSeekable()) {
            event.getChannel().sendMessage(TextChannelResponses.SEEK_POINT_LONGER_THAN_SONG).queue();
            return;
        }

        event.getChannel().sendMessage(String.format(TextChannelResponses.SEEKING_TO_INFORMATION,
                TimeUtils.timeString(seekTime))).queue();

        np.setPosition(seekTime * 1000);
    }

    private int getSeekTime(String seekPoint) throws IllegalArgumentException {
        int seekTime;
        if (seekPoint.contains(":")) {
            //it is in the format mins:seconds
            String[] parts = seekPoint.split(":");

            if (parts.length == 3) {
                // it is in hours minutes and seconds
                seekTime = Integer.parseInt(parts[0]) * 60 * 60;
                seekTime += Integer.parseInt(parts[1]) * 60;
                seekTime += Integer.parseInt(parts[2]);
            } else if (parts.length == 2) {
                //parts[0] is mins and parts[1] is seconds
                seekTime = Integer.parseInt(parts[0]) * 60;
                seekTime += Integer.parseInt(parts[1]);
            } else {
                throw new IllegalArgumentException("Too many : in seek command " + seekPoint);
            }
        } else {
            seekTime = Integer.parseInt(seekPoint);
        }
        return seekTime;
    }
}