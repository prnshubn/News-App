package com.wipro.newsapp.sports.controller;

import com.wipro.newsapp.sports.model.ListRestResponse;
import com.wipro.newsapp.sports.service.SportsNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SportsNewsController {

    @Autowired
    private SportsNewsService sportsNewsService;

    @GetMapping("/sports")
    public ResponseEntity<?> getNews(@RequestHeader("Authorization") String token) {
        ListRestResponse listRestResponse = new ListRestResponse();
        listRestResponse.setList(sportsNewsService.getNews(token));
        return new ResponseEntity<>(listRestResponse, HttpStatus.OK);
    }

}
