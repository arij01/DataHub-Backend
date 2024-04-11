package tn.esprit.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SolutionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionServiceApplication.class, args);
    }

}
