package bot.services;


import bot.repository.EntityGuildHolderRepository;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public interface DiscordBotService {

    void startLoad(EntityGuildHolderRepository entityGuildHolderRepository) throws LoginException;

    void off();

    JDA getJda();

}
