package bot.commands.alias;


import bot.listeners.CommandEventListener;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static bot.utils.ChannelTextResponses.NO_ALIASES_SET;

/**
 * can only send 2000 characters in a single message so put each alias description onto eachAliasDescription and
 * later combine ones that are less than 2000 characters into their own messages
 * Splitting a message so that it can display multiple aliases
 */

public class AliasListCommands extends Command{

    private final CommandEventListener commandEventListener;

    public AliasListCommands(CommandEventListener commandEventListener){
        this.name = "aliaslist";
        this.aliases = new String[]{"al"};
        this.help = "List all the aliases for this server";
        this.commandEventListener = commandEventListener;
    }

    @Override
    protected void execute(CommandEvent event) {

        String guildId = event.getGuild().getId();

        GuildAlliasHolders guildAliasHolder = commandEventListener.getGuildAliasHolderForGuildWithId(guildId);

        if (guildAliasHolder == null || guildAliasHolder.getAliasEntityList().size() == 0){

            event.getChannel().sendMessage(NO_ALIASES_SET).queue();

            return;
        }

        HashMap<String, Alias> aliasHashMap = guildAliasHolder.getAliasNameToAliasObject();

        List<Alias> aliases = new ArrayList<>();

        //Lambda function
        aliasHashMap.keySet().forEach(key -> {
            Alias alias = aliasHashMap.get(key);
            aliases.add(alias);
        });

        ArrayList<String> eachAliasDescription = new ArrayList<>();

    }
}
