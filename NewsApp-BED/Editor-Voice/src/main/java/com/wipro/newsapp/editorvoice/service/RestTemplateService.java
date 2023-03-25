package com.wipro.newsapp.editorvoice.service;

import com.wipro.newsapp.editorvoice.annotation.Generated;
import com.wipro.newsapp.editorvoice.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Generated
@Service
public class RestTemplateService {

    private final String url = "http://localhost:7070/authentication/user/authenticate";
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

    public List<Interaction> getReportsByEdiTitle(String ediTitle, String token){
        final String url = "http://localhost:7070/user/interaction/getReportByEdiTitle/{ediTitle}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<List> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                List.class, ediTitle);
        return authenticatedResponse.getBody();
    }
}