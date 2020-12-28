package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannel;
import bot.listeners.VoiceChannelEventListener;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class SkipSongCommand extends Command {

    public SkipSongCommand() {
        this.name = "skip";
        this.help = "Skip the currently playing song";
    }

    @Override
    protected void execute(CommandEvent event){

        AudioPlayerSendHandler audioPlayerSendHandler;
        try{
            audioPlayerSendHandler = VoiceChannel.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());
        }catch(IllegalArgumentException e){
            event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();
            return;

        }

        // disable looping
        audioPlayerSendHandler.getTrackScheduler().setLoopTrack(null);

        audioPlayerSendHandler.getAudioPlayer().stopTrack();
        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();

    }


}
