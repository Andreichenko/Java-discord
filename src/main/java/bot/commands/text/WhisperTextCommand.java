package bot.commands.text;

import bot.utils.commands.Command;
import bot.utils.commands.CommandEvent;
import net.dv8tion.jda.api.Permission;

import static bot.utils.TextChannelResponses.ECHO_COMMAND_NO_ARGS;

public class WhisperTextCommand extends Command {
    public WhisperTextCommand() {
        this.name = "whisper";
        this.aliases = new String[]{"w"};
        this.help = "Sends a message with the text that was passed in as an argument and then deletes the message that " +
                "triggered the command";
        this.botPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().delete().queue();

        String textToReturn = event.getArgs();

        if (textToReturn.isEmpty()) {
            event.getChannel().sendMessage(ECHO_COMMAND_NO_ARGS).queue();
            return;
        }

        event.getChannel().sendMessage(textToReturn).queue();
    }
}
