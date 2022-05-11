package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannelUtils;
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
            audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());
        }catch(IllegalArgumentException e){
            event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();
            return;

        }

        if (audioPlayerSendHandler != null) {
            // disable looping
            audioPlayerSendHandler.getTrackScheduler().setLoopTrack(null);

            audioPlayerSendHandler.getAudioPlayer().stopTrack();
            event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();
        }
    }


}
