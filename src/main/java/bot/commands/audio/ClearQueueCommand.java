package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
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

    }
}
