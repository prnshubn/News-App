package com.wipro.newsapp.userfeeder.service;

import com.wipro.newsapp.userfeeder.annotation.Generated;
import com.wipro.newsapp.userfeeder.model.ListRestResponseEdi;
import com.wipro.newsapp.userfeeder.model.ListRestResponseNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Generated
@Service
public class RestTemplateService {

    @Autowired
    RestTemplate restTemplate;


    public String authenticate(String token) {
        final String url = "http://localhost:7070/authentication/user/authenticate";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                String.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseEdi getAllEditorials(String token) {
        final String url = "http://localhost:7070/admin/editorial/getAllEditorials";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseEdi> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseEdi.class);
        return authenticatedResponse.getBody();
    }


    public ListRestResponseNews getAllNews(String token) {
        final String url = "http://localhost:7070/user/interaction/getAllNews";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseNews.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseNews getAllSportslNews(String token) {
        final String url = "http://localhost:7070/user/interaction/getAllSportsNews";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseNews.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseNews getAllBusinesslNews(String token) {
        final String url = "http://localhost:7070/user/interaction/getAllBusinessNews";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseNews.class);
        return authenticatedResponse.getBody();
    }

}