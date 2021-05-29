package saechim.interior.etcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EtcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtcServiceApplication.class, args);
    }

}
