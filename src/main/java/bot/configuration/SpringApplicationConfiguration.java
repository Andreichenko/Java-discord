package bot.configuration;


import bot.services.DiscordBotService;
import bot.services.implement.DiscordBotImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationConfiguration {


    @Bean
    public DiscordBotService botService(){
        return new DiscordBotImplement();
    }
}
