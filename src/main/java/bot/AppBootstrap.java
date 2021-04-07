package bot;


import bot.repository.EntityGuildHolderRepository;
import bot.services.DiscordBotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * This bot is deployed in a separate container and connected to your platform ID
 * Based on Java Springboot Framework 2.3.1
 */

@SpringBootApplication
@EnableConfigurationProperties
public class AppBootstrap implements CommandLineRunner{


    private final DiscordBotService discordBotService;

    public AppBootstrap(DiscordBotService discordBotService, EntityGuildHolderRepository entityGuildHolderRepository){
        this.discordBotService = discordBotService;

    }

    public static void main(String[] args) {

        SpringApplication.run(AppBootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.discordBotService.startLoad();
    }
}
