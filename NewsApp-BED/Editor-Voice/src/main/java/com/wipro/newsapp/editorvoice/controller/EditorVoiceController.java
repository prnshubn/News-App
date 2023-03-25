package com.wipro.newsapp.editorvoice.controller;

import com.wipro.newsapp.editorvoice.exception.EditorialAlreadyExistsException;
import com.wipro.newsapp.editorvoice.exception.EditorialNotFoundException;
import com.wipro.newsapp.editorvoice.model.Editorial;
import com.wipro.newsapp.editorvoice.model.ListRestResponse;
import com.wipro.newsapp.editorvoice.repository.InteractionRepository;
import com.wipro.newsapp.editorvoice.service.EditorVoiceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/editorial")
@CrossOrigin(origins = "http://localhost:4200")
public class EditorVoiceController {

    @Autowired
    private EditorVoiceService editorVoiceService;

    @Autowired
    private InteractionRepository interactionRepository;

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PostMapping("/addEditorial")
    public ResponseEntity<?> addEditorial(@RequestBody Editorial edi, @RequestHeader("Authorization") String token) {
        try {
            Editorial e = editorVoiceService.saveEditorial(edi, token);
            return new ResponseEntity<Editorial>(e, HttpStatus.CREATED);
        } catch (EditorialAlreadyExistsException e) {
        }
        return new ResponseEntity<String>("failed to create editorial", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> serviceDown(Editorial edi, String token,Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }


        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getEditorialByTitle/{title}")
    public ResponseEntity<?> getEditorialByTitle(@PathVariable String title, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            Editorial edi = editorVoiceService.getEditorialByTitle(title, token);
            responseEntity = new ResponseEntity<Editorial>(edi, HttpStatus.OK);
        } catch (EditorialNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    public ResponseEntity<?> serviceDown(String title, String token,Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }


        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllEditorials")
    public ResponseEntity<?> getAllEditorials(@RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            List<Editorial> ediList = editorVoiceService.getAllEditorials(token);
            ListRestResponse listRestResponse = new ListRestResponse();
            listRestResponse.setList(ediList);
            responseEntity = new ResponseEntity(listRestResponse, HttpStatus.OK);
        } catch (EditorialNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    public ResponseEntity<?> serviceDown(String token,Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }

        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PutMapping("/updateEditorial")
    public ResponseEntity<?> updateEditorial(@RequestBody Editorial edi, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<Editorial>(editorVoiceService.updateEditorial(edi, token), HttpStatus.OK);
    }
    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @DeleteMapping("/delete/editorial/{title}")
    public ResponseEntity<?> deleteEditorial(@PathVariable String title, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            editorVoiceService.deleteEditorial(title, token);
            responseEntity = new ResponseEntity<String>("editorial deleted successfully!", HttpStatus.OK);
        } catch (EditorialNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/report/{title}")
    public ResponseEntity<?> viewReportEditorial(@PathVariable String title, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(editorVoiceService.viewReportEditorial(title, token), HttpStatus.OK);
        } catch (EditorialNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
