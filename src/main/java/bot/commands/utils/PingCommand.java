package bot.commands.utils;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class PingCommand extends Command {

    public PingCommand() {

        this.name = "ping";
        this.help = "Check the bot's response time to Discord";
    }

    @Override
    protected void execute(CommandEvent event) {

        long ping = event.getJDA().getGatewayPing();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(String.format(":hourglass: %dms", ping));
        embedBuilder.setColor(Color.GREEN);
        event.getChannel().sendMessage(embedBuilder.build()).queue();


    }
}
