package bot.listeners;

import bot.commands.alias.GuildAlliasHolders;
import com.jagrosh.jdautilities.command.CommandClient;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class CommandEventListener extends ListenerAdapter{

    private final HashMap<String, GuildAlliasHolders> guildIdToGuildAliasHolderMap = new HashMap<>();

    private CommandClient commandClient;
}
