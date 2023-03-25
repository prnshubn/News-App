package com.wipro.newsapp.userservice.service;

import com.wipro.newsapp.userservice.model.ListRestResponse;
import com.wipro.newsapp.userservice.model.ListRestResponseAllNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    private final String url1 = "http://localhost:7070/authentication/user/authenticate";
    private final String url2 = "http://localhost:7070/admin/editorial/getAllEditorials";
    private final String url3 = "http://localhost:7070/api/top";
    @Autowired
    RestTemplate restTemplate;

    public String authenticate(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> authenticatedResponse = restTemplate.exchange(url1, HttpMethod.GET, request,
                String.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponse getAllEditorials(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponse> authenticatedResponse = restTemplate.exchange(url2, HttpMethod.GET, request,
                ListRestResponse.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseAllNews getAllNews(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseAllNews> authenticatedResponse = restTemplate.exchange(url3, HttpMethod.GET, request,
                ListRestResponseAllNews.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseAllNews getAllSportsNews(String token) {
        final String url = "http://localhost:7070/api/sports";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseAllNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseAllNews.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseAllNews getAllBusinessNews(String token) {
        final String url = "http://localhost:7070/api/business";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseAllNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseAllNews.class);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseAllNews getAllSearchNews(String key, String token) {
        final String url = "http://localhost:7070/api/search/{key}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ListRestResponseAllNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseAllNews.class, key);
        return authenticatedResponse.getBody();
    }

}