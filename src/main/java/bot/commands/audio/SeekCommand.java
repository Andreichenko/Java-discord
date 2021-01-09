package bot.commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class SeekCommand extends Command {


    @Override
    protected void execute(CommandEvent event) {

        AudioManager audioManager = event.getGuild().getAudioManager();
    }

    private int getSeekTime(String seekPoint) {
        
    }
}
