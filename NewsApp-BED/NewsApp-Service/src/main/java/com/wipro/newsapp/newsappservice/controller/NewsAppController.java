package com.wipro.newsapp.newsappservice.controller;

import com.wipro.newsapp.newsappservice.model.FavEditorial;
import com.wipro.newsapp.newsappservice.model.News;
import com.wipro.newsapp.newsappservice.service.NewsAppService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newsapp/service")
public class NewsAppController {

    @Autowired
    private NewsAppService newsAppService;

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addNews")
    public ResponseEntity<?> addNews(@RequestBody News news, @RequestHeader("Authorization") String token) {
        newsAppService.addNews(news, token);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    public ResponseEntity<?> serviceDown(News news, String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addSportsNews")
    public ResponseEntity<?> addSportsNews(@RequestBody News news, @RequestHeader("Authorization") String token) {
        newsAppService.addSportsNews(news, token);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }


    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addBusinessNews")
    public ResponseEntity<?> addBusinessNews(@RequestBody News news, @RequestHeader("Authorization") String token) {
        newsAppService.addBusinessNews(news, token);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addEditorial")
    public ResponseEntity<?> addEditorial(@RequestBody FavEditorial favEditorial, @RequestHeader("Authorization") String token) {
        newsAppService.addEditorial(favEditorial, token);
        return new ResponseEntity<>(favEditorial, HttpStatus.OK);
    }

    public ResponseEntity<?> serviceDown(FavEditorial favEditorial, String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }


    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @DeleteMapping("/delete/{email}/{title}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable String title, @PathVariable String email) {
        return new ResponseEntity<>(newsAppService.delete(token, title, email), HttpStatus.OK);
    }

    public ResponseEntity<?> serviceDown(String token, String email, String title, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }


    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getFavouriteNews/{email}")
    public ResponseEntity<?> getAllNews(@PathVariable String email, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(newsAppService.getAllNews(email, token), HttpStatus.OK);
    }

    public ResponseEntity<?> serviceDown(String email, String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }


    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getFavouriteEditorials/{email}")
    public ResponseEntity<?> getAllEditorials(@PathVariable String email, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(newsAppService.getAllEditorials(email, token), HttpStatus.OK);
    }


}
