package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.VoiceChannelUtils;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ClearQueueCommand extends Command {

    public ClearQueueCommand(){

        this.name = "clear";
        this.help = "Clear the queue for this server";
    }

    @Override
    protected void execute(CommandEvent event) {

            AudioPlayerSendHandler audioPlayerSendHandler;

            try {

             audioPlayerSendHandler = VoiceChannelUtils.getAudioPlayerSendHandler(event.getJDA(), event.getGuild().getId());

            } catch (IllegalArgumentException ex) {

                event.getChannel().sendMessage("**Not currently connected to the voice channel**").queue();
                return;
        }

        audioPlayerSendHandler.getTrackScheduler().clearQueue();
        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();

    }
}
