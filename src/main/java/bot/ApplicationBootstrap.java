package bot;

import bot.services.BotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * This bot is deployed in a separate container and connected to your platform ID
 * Based on Java Springboot Framework 2.3.1 need to rework
 */

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationBootstrap implements CommandLineRunner {

    private final BotService botService;

    public ApplicationBootstrap(BotService botService) {
        this.botService = botService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        this.botService.startBot();
    }
}
