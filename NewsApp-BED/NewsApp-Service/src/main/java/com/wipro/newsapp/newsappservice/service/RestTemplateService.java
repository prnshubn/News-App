package com.wipro.newsapp.newsappservice.service;

import com.wipro.newsapp.newsappservice.annotation.Generated;
import com.wipro.newsapp.newsappservice.model.Favourite;
import com.wipro.newsapp.newsappservice.model.ListRestResponseEdi;
import com.wipro.newsapp.newsappservice.model.ListRestResponseNews;
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

    public Favourite addFavourites(String token, Favourite favourite) {
        final String url1 = "http://localhost:7070/user/feeder/addFavourites";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(favourite, headers);
        ResponseEntity<Favourite> authenticatedResponse = restTemplate.exchange(url1, HttpMethod.POST, request,
                Favourite.class);
        return authenticatedResponse.getBody();
    }

    public Favourite deleteFavourite(String token, String title, String email) {
        final String url2 = "http://localhost:7070/user/feeder/delete/{email}/{title}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<Favourite> authenticatedResponse = restTemplate.exchange(url2, HttpMethod.DELETE, request,
                Favourite.class, email, title);
        return authenticatedResponse.getBody();
    }

    public ListRestResponseEdi getAllFavouritesEditorial(String email, String token, String isNews) {

        final String url = "http://localhost:7070/user/feeder/getAllFavouriteEditorials/{email}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<ListRestResponseEdi> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseEdi.class, email);


        return authenticatedResponse.getBody();
    }

    public ListRestResponseNews getAllFavouritesNews(String email, String token) {
        final String url = "http://localhost:7070/user/feeder/getAllFavouriteNews/{email}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<ListRestResponseNews> authenticatedResponse = restTemplate.exchange(url, HttpMethod.GET, request,
                ListRestResponseNews.class, email);


        return authenticatedResponse.getBody();
    }
}