package com.wipro.newsapp.userfeeder.controller;


import com.wipro.newsapp.userfeeder.annotation.Generated;
import com.wipro.newsapp.userfeeder.exception.CustomException;
import com.wipro.newsapp.userfeeder.model.*;
import com.wipro.newsapp.userfeeder.service.UserFeederService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/feeder")
@CrossOrigin(origins = "http://localhost:4200")
public class UserFeederController {
    @Autowired
    private UserFeederService userFeederService;

    @Generated
    public String getEmail(String token) {
        String email = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.substring(7)).getBody()
                .getSubject();
        return email;
    }

    @PostMapping("/addFavourites")
    public ResponseEntity<?> saveFavourites(@RequestBody Favourite favourite, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            userFeederService.saveFavourites(favourite, token);
            responseEntity = new ResponseEntity<Favourite>(favourite, HttpStatus.CREATED);
        } catch (CustomException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{email}/{title}")
    public ResponseEntity<?> deleteFavourite(@RequestHeader("Authorization") String token, @PathVariable String title, @PathVariable String email) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userFeederService.deleteFavourite(token, title, email), HttpStatus.OK);
        } catch (CustomException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }



    @GetMapping("/getAllFavouriteEditorials/{email}")
    public ResponseEntity<?> getMyFavouriteEditorials(@PathVariable String email, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            ListRestResponseEdi listRestResponseEdi = new ListRestResponseEdi();

            List<Editorial> favouriteEditorialList = userFeederService.getFavouritesEditorial(email, token);
            listRestResponseEdi.setList(favouriteEditorialList);

            responseEntity = new ResponseEntity<>(listRestResponseEdi, HttpStatus.OK);
        } catch (CustomException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }



    @GetMapping("/getAllFavouriteNews/{email}")
    public ResponseEntity<?> getMyFavouriteNews(@PathVariable String email, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            ListRestResponseNews listRestResponseNews = new ListRestResponseNews();

            List<News> favouriteNewsList = userFeederService.getFavouritesNews(email, token);
            listRestResponseNews.setList(favouriteNewsList);

            responseEntity = new ResponseEntity<>(listRestResponseNews, HttpStatus.OK);
        } catch (CustomException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}