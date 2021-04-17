package bot.commands.alias;


import bot.entities.AliasEntity;
import bot.repositories.AliasEntityRepository;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static bot.utils.TextChannelResponses.NO_ALIASES_SET;

/**
 * can only send 2000 characters in a single message so put each alias description onto eachAliasDescription and
 * later combine ones that are less than 2000 characters into their own messages
 * Splitting a message so that it can display multiple aliases
 */
@Component
public class AliasListCommand extends Command{

    private final AliasEntityRepository aliasEntityRepository;

    @Autowired
    public AliasListCommand(AliasEntityRepository aliasEntityRepository){
        this.name = "aliaslist";
        this.aliases = new String[]{"al"};
        this.help = "List all the aliases for this server";

        this.aliasEntityRepository = aliasEntityRepository;
    }

    @Override
    protected void execute(CommandEvent event){
        String guildId = event.getGuild().getId();
        Set<AliasEntity> aliasEntitySet = aliasEntityRepository.findAllByServerId(guildId);

        if (aliasEntitySet.size() == 0){
            event.getChannel().sendMessage(NO_ALIASES_SET).queue();
            return;
        }

        // can only send 2000 characters in a single message so put each alias description onto eachAliasDescription and
        // later combine ones that are less than 2000 characters into their own messages
        ArrayList<String> eachAliasDescription = new ArrayList<>();

        int i = 1;

        for (AliasEntity alias : aliasEntitySet){
            StringBuilder aliasListString = new StringBuilder();
            aliasListString.append("`").append(i).append(":` `");
            aliasListString.append(alias.getName());
            aliasListString.append("` executes command `");
            aliasListString.append(alias.getCommand());
            aliasListString.append("` with arguments `");
            aliasListString.append(alias.getArgs());
            aliasListString.append("`");
            aliasListString.append("\n");
            i++;
            eachAliasDescription.add(aliasListString.toString());
        }

        ArrayList<String> fullMessagesToSend = new ArrayList<>();

        int index = -1;
        for (String aliasDescription : eachAliasDescription){

            if (fullMessagesToSend.isEmpty()){
                fullMessagesToSend.add(aliasDescription);
                index++;
            } else {
                String previousMessage = fullMessagesToSend.get(index);
                if (aliasDescription.length() + previousMessage.length() < 2000){
                    fullMessagesToSend.remove(index);
                    aliasDescription = previousMessage + aliasDescription;
                    fullMessagesToSend.add(aliasDescription);
                }else {
                    fullMessagesToSend.add(aliasDescription);
                    index++;
                }
            }
        }
        fullMessagesToSend.forEach(message -> event.getChannel().sendMessage(message).queue());
    }

}
