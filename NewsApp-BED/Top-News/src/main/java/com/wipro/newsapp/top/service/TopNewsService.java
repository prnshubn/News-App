package com.wipro.newsapp.top.service;

import com.wipro.newsapp.top.model.ResultantObject;
import com.wipro.newsapp.top.model.TopNews;
import com.wipro.newsapp.top.repository.TopNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopNewsService {

    @Autowired
    public TopNewsRepository topNewsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    public TopNews saveNews(TopNews news) {
        return topNewsRepository.save(news);
    }

   public ArrayList<TopNews> getNewsFromApi() {
       String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=be153b5e140e4359b38b752bd59e4b14";
       ResultantObject res = restTemplate.getForObject(url, ResultantObject.class);
       ArrayList<TopNews> newsList = new ArrayList<>();
       for (TopNews news : res.getArticles()) {
           news.setNews(true);
           news.setNewsType("top");
           topNewsRepository.save(news);
           newsList.add(news);
       }
       return newsList;
   }

    public List<TopNews> getNews(String token) {
        authenticationService.authenticate(token);
        getNewsFromApi();
        List<TopNews> list = topNewsRepository.findAll();
        return list;
    }
}
