package bot.services.implement;

import bot.repository.EntityGuildHolderRepository;
import bot.services.DiscordBotService;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;

public class DiscordBotImplement implements DiscordBotService{

    @Override
    public void startLoad(EntityGuildHolderRepository entityGuildHolderRepository) throws LoginException {

    }

    @Override
    public void off() {

    }

    @Override
    public JDA getJda() {
        return null;
    }
}
