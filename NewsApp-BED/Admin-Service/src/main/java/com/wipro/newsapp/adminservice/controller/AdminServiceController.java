package com.wipro.newsapp.adminservice.controller;

import com.wipro.newsapp.adminservice.annotation.Generated;
import com.wipro.newsapp.adminservice.model.User;
import com.wipro.newsapp.adminservice.service.AdminService;
import com.wipro.newsapp.adminservice.util.JwtUtility;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authentication/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminServiceController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtility jwtUtility;

    @Generated
    @PostConstruct
    public void initRolesAndUsers() {
        adminService.initRolesAndUsers();
    }

    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return new ResponseEntity<>(adminService.deleteUser(email), HttpStatus.OK);
    }

    public ResponseEntity<String> serviceDown(String email,Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }

        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }
    public List<User> serviceDown(Exception e) {

        List<User> list=new ArrayList<>();
        User user=new User();
        list.add(user);
        return list;
    }

        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PutMapping("/activateUser/{email}")
    public ResponseEntity<?> activateUser(@PathVariable String email) {
        return new ResponseEntity<>(adminService.activateUser(email), HttpStatus.OK);
    }



        @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody User user) {
        return new ResponseEntity<User>(adminService.updatePassword(user), HttpStatus.OK);
    }

    public ResponseEntity<?> serviceDown(User user,Exception e) {
        return new ResponseEntity<String>("Service down! Please try after sometime.", HttpStatus.SERVICE_UNAVAILABLE);

    }
    @CircuitBreaker(name = "serviceDown", fallbackMethod = "serviceDown")
    @PutMapping("/deactivateUser/{email}")
    public ResponseEntity<?> deactivateUser(@PathVariable String email) {
        return new ResponseEntity<>(adminService.deactivateUser(email), HttpStatus.OK);
    }
}
