package bot.commands.alias;

import bot.entities.AliasEntity;
import bot.repositories.AliasEntityRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static bot.utils.TextChannelResponses.ALIAS_DELETE_ALIAS_DOES_NOT_EXIST;
import static bot.utils.TextChannelResponses.ALIAS_DELETE_NONE_PROVIDED;
import static bot.utils.TextChannelResponses.ALIAS_REMOVED;


@Component
public class AliasDeleteCommand extends Command {

    private final AliasEntityRepository aliasEntityRepository;

    @Autowired
    public AliasDeleteCommand(AliasEntityRepository aliasEntityRepository){
        this.name = "aliasdelete";
        this.help = "Delete an alias with a specific name";
        this.aliases = new String[]{"ad"};

        this.aliasEntityRepository = aliasEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event){
        String aliasToDelete = event.getArgs();

        if (aliasToDelete.isEmpty()){
            event.getChannel().sendMessage(ALIAS_DELETE_NONE_PROVIDED).queue();
            return;
        }

        String guildId = event.getGuild().getId();

        AliasEntity aliasEntityToDelete = aliasEntityRepository.findByServerIdAndName(guildId, aliasToDelete);


        if (aliasEntityToDelete == null){
            event.getChannel().sendMessage(String.format(ALIAS_DELETE_ALIAS_DOES_NOT_EXIST, aliasToDelete)).queue();
            return;
        }

        aliasEntityRepository.delete(aliasEntityToDelete);


        event.getChannel().sendMessage(String.format(ALIAS_REMOVED, aliasToDelete)).queue();
    }
}
