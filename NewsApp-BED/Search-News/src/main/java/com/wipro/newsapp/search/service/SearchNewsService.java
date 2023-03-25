package com.wipro.newsapp.search.service;

import com.wipro.newsapp.search.model.ResultantObject;
import com.wipro.newsapp.search.model.SearchNews;
import com.wipro.newsapp.search.reporsitory.SearchNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchNewsService {

    @Autowired
    public SearchNewsRepository searchNewsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    public SearchNews saveNews(SearchNews news) {
        return searchNewsRepository.save(news);
    }

    public ArrayList<SearchNews> getNewsFromApi(String key) {
        String url = "https://newsapi.org/v2/everything?q=" + key + "&apiKey=be153b5e140e4359b38b752bd59e4b14";
        ResultantObject res = restTemplate.getForObject(url, ResultantObject.class);
        ArrayList<SearchNews> newsList = new ArrayList<>();
        for (SearchNews news : res.getArticles()) {
            news.setNews(true);
            news.setNewsType("search");
            searchNewsRepository.save(news);
            newsList.add(news);
        }
        return newsList;
    }

    public List<SearchNews> getNews(String key, String token) {
        authenticationService.authenticate(token);
        searchNewsRepository.deleteAll();
        getNewsFromApi(key);
        List<SearchNews> list = searchNewsRepository.findAll();
        return list;
    }
}
