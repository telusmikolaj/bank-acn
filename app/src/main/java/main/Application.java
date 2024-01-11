package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.accenture.api.*",
                "com.accenture.entity.*",
                "com.accenture.service"
        }
)
@EnableJpaRepositories("com.accenture.entity.repository")
@EntityScan("com.accenture.entity.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
