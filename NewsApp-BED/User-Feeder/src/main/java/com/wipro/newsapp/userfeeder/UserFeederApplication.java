package com.wipro.newsapp.userfeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserFeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserFeederApplication.class, args);
    }

}
