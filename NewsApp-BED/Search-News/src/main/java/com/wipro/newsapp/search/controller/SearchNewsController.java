package com.wipro.newsapp.search.controller;

import com.wipro.newsapp.search.model.ListRestResponse;
import com.wipro.newsapp.search.service.SearchNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SearchNewsController {

    @Autowired
    private SearchNewsService topNewsService;

    @GetMapping("/search/{key}")
    public ResponseEntity<?> getNews(@PathVariable String key, @RequestHeader("Authorization") String token) {
        ListRestResponse listRestResponse = new ListRestResponse();
        listRestResponse.setList(topNewsService.getNews(key, token));
        return new ResponseEntity<>(listRestResponse, HttpStatus.OK);
    }

}
