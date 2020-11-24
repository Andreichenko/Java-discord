package bot.services.implement;

import bot.repository.EntityGuildHolderRepository;
import bot.services.DiscordBotService;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Value;

import javax.security.auth.login.LoginException;

public class DiscordBotImplement implements DiscordBotService{

    @Value("${DISCORD_BOT_KEY}")
    private String DISCORD_BOT_KEY;

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
