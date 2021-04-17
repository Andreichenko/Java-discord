package bot.commands.audio;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import bot.utils.UnicodeMotion;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static bot.utils.TextChannelResponses.REMOVE_COMMAND_NOT_A_NUMBER;
import static bot.utils.TextChannelResponses.REMOVE_COMMAND_NO_ARGUMENT;
import static bot.utils.TextChannelResponses.REMOVE_COMMAND_NO_TRACK_TO_REMOVE;

public class RemoveCommand extends Command {

    private final Logger LOGGER = LogManager.getLogger(RemoveCommand.class);


    public RemoveCommand(){
        this.name = "remove";
        this.help = "Remove the requested song from the queue.";
    }

    @Override
    protected void execute(CommandEvent event) {

        String argument = event.getArgs();

        if (argument.isEmpty()){

            event.getChannel().sendMessage(REMOVE_COMMAND_NO_ARGUMENT).queue();
            return;
        }

        int trackToRemove;

        try {
            trackToRemove = Integer.parseInt(argument);
        } catch(NumberFormatException e) {
            event.getChannel().sendMessage(String.format(REMOVE_COMMAND_NOT_A_NUMBER, argument)).queue();
            return;
        }
        LOGGER.info("Removing track {} from the queue", trackToRemove);

        AudioManager audioManager = event.getGuild().getAudioManager();


        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();

        if (audioPlayerSendHandler == null){

            event.getChannel().sendMessage(String.format(REMOVE_COMMAND_NO_TRACK_TO_REMOVE, trackToRemove)).queue();
            return;
        }

        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();

        try {
            trackScheduler.remove(trackToRemove - 1);
        }catch(IndexOutOfBoundsException e){
            LOGGER.info("Track {} is not a track on the queue", trackToRemove);
            event.getChannel().sendMessage(String.format(REMOVE_COMMAND_NO_TRACK_TO_REMOVE, trackToRemove)).queue();
            return;
        }
        event.getMessage().addReaction(UnicodeMotion.THUMBS_UP).queue();

    }
}
