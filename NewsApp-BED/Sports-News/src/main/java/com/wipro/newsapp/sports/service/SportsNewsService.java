package com.wipro.newsapp.sports.service;

import com.wipro.newsapp.sports.model.ResultantObject;
import com.wipro.newsapp.sports.model.SportsNews;
import com.wipro.newsapp.sports.repository.SportsNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportsNewsService {

    @Autowired
    public SportsNewsRepository sportsNewsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    public SportsNews saveNews(SportsNews news) {
        return sportsNewsRepository.save(news);
    }

    public ArrayList<SportsNews> getNewsFromApi() {
        String url = "https://newsapi.org/v2/top-headlines?category=sports&apiKey=be153b5e140e4359b38b752bd59e4b14";
        ResultantObject res = restTemplate.getForObject(url, ResultantObject.class);
        ArrayList<SportsNews> newsList = new ArrayList<>();
        for (SportsNews news : res.getArticles()) {
            news.setNews(true);
            news.setNewsType("sports");
            sportsNewsRepository.save(news);
            newsList.add(news);
        }
        return newsList;
    }

    public List<SportsNews> getNews(String token) {
        authenticationService.authenticate(token);
        getNewsFromApi();
        List<SportsNews> list = sportsNewsRepository.findAll();
        return list;
    }
}
