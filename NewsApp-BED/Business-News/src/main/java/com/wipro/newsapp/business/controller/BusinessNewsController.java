package com.wipro.newsapp.business.controller;

import com.wipro.newsapp.business.model.ListRestResponse;
import com.wipro.newsapp.business.service.BusinessNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusinessNewsController {

    @Autowired
    private BusinessNewsService topNewsService;

    @GetMapping("/business")
    public ResponseEntity<?> getNews(@RequestHeader("Authorization") String token) {
        ListRestResponse listRestResponse = new ListRestResponse();
        listRestResponse.setList(topNewsService.getNews(token));
        return new ResponseEntity<>(listRestResponse, HttpStatus.OK);
    }

}
