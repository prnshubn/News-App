package com.wipro.newsapp.business.service;

import com.wipro.newsapp.business.model.BusinessNews;
import com.wipro.newsapp.business.model.ResultantObject;
import com.wipro.newsapp.business.repository.BusinessNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessNewsService {

    @Autowired
    public BusinessNewsRepository topNewsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    public BusinessNews saveNews(BusinessNews news) {
        return topNewsRepository.save(news);
    }

    public ArrayList<BusinessNews> getNewsFromApi() {
        String url = "https://newsapi.org/v2/top-headlines?category=business&apiKey=be153b5e140e4359b38b752bd59e4b14";
        ResultantObject res = restTemplate.getForObject(url, ResultantObject.class);
        ArrayList<BusinessNews> newsList = new ArrayList<>();
        for (BusinessNews news : res.getArticles()) {
            news.setNews(true);
            news.setNewsType("business");
            topNewsRepository.save(news);
            newsList.add(news);
        }
        return newsList;
    }

    public List<BusinessNews> getNews(String token) {
        authenticationService.authenticate(token);
        getNewsFromApi();
        List<BusinessNews> list = topNewsRepository.findAll();
        return list;
    }
}
