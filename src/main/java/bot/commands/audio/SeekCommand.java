package bot.commands.audio;

import bot.utils.ChannelTextResponses;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class SeekCommand extends Command {


    @Override
    protected void execute(CommandEvent event) {

        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()){

            event.getChannel().sendMessage(ChannelTextResponses.BOT_NOT_CONNECTED_TO_VOICE).queue();
            return;
        }

        String seekPoint = event.getArgs();
        int seekTime;
    }

//    private int getSeekTime(String seekPoint) {
//
//        int seekTime;
//        return seekTime;
//    }
}
