package bot.configuration;


import bot.services.BotService;
import bot.services.implement.BotImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationConfiguration {


    @Bean
    public BotService botService(){
        return new BotImplement();
    }
}
