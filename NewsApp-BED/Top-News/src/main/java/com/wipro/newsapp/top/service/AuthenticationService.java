package com.wipro.newsapp.top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private final String url = "http://localhost:8080/authentication/user/authenticate";
    @Autowired
    RestTemplate restTemplate;

    public String authenticate(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                String.class);
        return authenticatedResponse.getBody();
    }
}
