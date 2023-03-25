package com.wipro.newsapp.userservice.controller;

import com.wipro.newsapp.userservice.exception.LikeStatusErrorException;
import com.wipro.newsapp.userservice.exception.ReportAlreadyExistException;
import com.wipro.newsapp.userservice.exception.ReportNotFoundException;
import com.wipro.newsapp.userservice.model.Interaction;
import com.wipro.newsapp.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/interaction")
public class UserServiceController {

    @Autowired
    private UserService userService;


    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addReport")
    public ResponseEntity<?> addReport(@RequestBody Interaction interaction, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.addReport(interaction.getEmail(), interaction.getEdiTitle(), interaction.getReport(), token), HttpStatus.OK);
        } catch (ReportAlreadyExistException e) {
            responseEntity = new ResponseEntity<>("couldn't add report", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<?> serviceDown(Interaction interaction, String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getReportByEdiTitle/{ediTitle}")
    public ResponseEntity<?> getReportsByEdiTitle(@PathVariable String ediTitle, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.getReportByEdiTitle(ediTitle, token), HttpStatus.FOUND);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("No reports found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    public ResponseEntity<?> serviceDown(String ediTitle, String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addLike")
    public ResponseEntity<?> addLike(@RequestBody Interaction interaction, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.addLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token), HttpStatus.OK);

        } catch (LikeStatusErrorException e) {
            responseEntity = new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/deleteLike")
    public ResponseEntity<?> deleteLikeStatus(@RequestBody Interaction interaction, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.deleteLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token), HttpStatus.OK);

        } catch (LikeStatusErrorException e) {
            responseEntity = new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @DeleteMapping("/deleteReport")
    public ResponseEntity<?> deleteReport(@RequestBody Interaction interaction, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.deleteReport(interaction.getEmail(), interaction.getEdiTitle(), token), HttpStatus.OK);

        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllEditorials")
    public ResponseEntity<?> getAllEditorials(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(userService.getAllEditorials(token), HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<?> serviceDown(String token, Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);


    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllNews")
    public ResponseEntity<?> getAllNews(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(userService.getAllNews(token), HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllSportsNews")
    public ResponseEntity<?> getAllSportsNews(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(userService.getAllSportsNews(token), HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllBusinessNews")
    public ResponseEntity<?> getAllBusinessNews(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(userService.getAllBusinessNews(token), HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllSearchedNews/{key}")
    public ResponseEntity<?> getAllSearchedNews(@PathVariable String key, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(userService.getAllSearchedNews(key, token), HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            responseEntity = new ResponseEntity<>("error occurred", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }


}