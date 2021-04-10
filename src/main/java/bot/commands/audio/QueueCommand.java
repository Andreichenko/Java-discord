package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class QueueCommand extends Command {

    public QueueCommand() {
        this.name = "queue";
        this.help = "See the queue for the server";
    }

    @Override
    protected void execute(CommandEvent event) {

        AudioManager audioManager = event.getGuild().getAudioManager();
        MessageChannel channel = event.getChannel();

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        if (audioPlayerSendHandler == null){
            channel.sendMessage("**Queue is empty**").queue();
            return;
        }
    }
}
