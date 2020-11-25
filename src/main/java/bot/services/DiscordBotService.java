package bot.services;


import bot.repository.EntityGuildHolderRepository;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public interface DiscordBotService {

    /**
     *
     * @param entityGuildHolderRepository this is an implementation of the basic functions of a bot for broadcasting sound
     *                                   and text messages
     * @throws LoginException  some exception
     */
    void startLoad(EntityGuildHolderRepository entityGuildHolderRepository) throws LoginException;

    void off();

    // this is Discord api
    JDA getJda();

}
