package saechim.interior.storyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoryServiceApplication.class, args);
    }

}
