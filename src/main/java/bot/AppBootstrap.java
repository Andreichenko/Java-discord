package bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AppBootstrap implements CommandLineRunner{

    public static void main(String[] args) {

        SpringApplication.run(AppBootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
