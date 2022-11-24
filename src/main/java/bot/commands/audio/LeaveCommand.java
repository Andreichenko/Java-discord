package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannelUtils;
import bot.utils.UnicodeEmote;
import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand extends Command
{
    public LeaveCommand() {
        this.name = "leave";
        this.aliases = new String[]{"die", "stop"};
        this.help = "Leave the currently connected voice channel";
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        AudioPlayerSendHandler audioPlayerSendHandler;
        try {
            audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());
        }
        catch(IllegalArgumentException e) {
            event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();
            return;
        }

        // tell the track scheduler that the bot is leaving the vc and not to find related videos.
        audioPlayerSendHandler.getTrackScheduler().setGotLeaveMessage(true);
        audioPlayerSendHandler.getAudioPlayer().stopTrack();
        audioManager.closeAudioConnection();
        audioPlayerSendHandler.getTrackScheduler().clearQueue();
        event.getMessage().addReaction(UnicodeEmote.THUMBS_UP).queue();
    }
}