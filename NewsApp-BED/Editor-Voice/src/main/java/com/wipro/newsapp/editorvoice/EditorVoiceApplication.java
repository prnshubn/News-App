package com.wipro.newsapp.editorvoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EditorVoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EditorVoiceApplication.class, args);
    }

}
