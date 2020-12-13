package bot.commands.alias;


import bot.listeners.CommandEventListener;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

//Splitting a message so that it can display multiple aliases
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


    }
}
