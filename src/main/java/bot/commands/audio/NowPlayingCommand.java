package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannelUtils;
import bot.commands.audio.utils.YouTubeUtils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.Color;

public class NowPlayingCommand extends Command {

    public NowPlayingCommand() {
        this.name = "nowplaying";
        this.aliases = new String[]{"np", "now playing"};
        this.help = "Get the currently playing song";
    }


    @Override
    protected void execute(CommandEvent event) {
        AudioPlayerSendHandler audioPlayerSendHandler;
        try {
            audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());
        } catch(IllegalArgumentException e) {
            event.getChannel().sendMessage(TextChannelResponses.BOT_NOT_CONNECTED_TO_VOICE).queue();
            return;
        }

        AudioTrack np = audioPlayerSendHandler.getAudioPlayer().getPlayingTrack();

        if (np == null) {
            event.getChannel().sendMessage(TextChannelResponses.NOTHING_CURRENTLY_PLAYING).queue();
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        if (np instanceof YoutubeAudioTrack) {
            String url = YouTubeUtils.getYoutubeThumbnail(np);
            eb.setThumbnail(url);
            eb.setColor(Color.RED);
        }
        eb.setAuthor("Now playing");
        eb.setTitle(np.getInfo().title, np.getInfo().uri);
        eb.setDescription(String.format("%s / %s", TimeLineStamp.timeString(np.getPosition() / 1000),
                TimeLineStamp.timeString(np.getDuration() / 1000)));
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
