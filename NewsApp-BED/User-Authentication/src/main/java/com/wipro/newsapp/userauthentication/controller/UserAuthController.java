package com.wipro.newsapp.userauthentication.controller;

import com.wipro.newsapp.userauthentication.exception.UserAlreadyExistsException;
import com.wipro.newsapp.userauthentication.model.User;
import com.wipro.newsapp.userauthentication.service.UserDetailsServiceImpl;
import com.wipro.newsapp.userauthentication.service.UserService;
import com.wipro.newsapp.userauthentication.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authentication/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        try {
            User u = userService.signup(user);
            return new ResponseEntity<User>(u, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
        }
        return new ResponseEntity<String>("failed to create user", HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@ModelAttribute("email") String email,
                                       @ModelAttribute("password") String password) {
        User u = userService.login(email, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtility.generateToken(userDetails);
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("role", userService.getRole(email));
        map.put("status", userService.getStatus(email));
        ResponseEntity<Map<String, String>> response = new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody User user) {
        return new ResponseEntity<User>(userService.updatePassword(user), HttpStatus.OK);
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(userService.logoutUser(token.substring(7)), HttpStatus.OK);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        return new ResponseEntity<String>("authentication success", HttpStatus.OK);
    }

}
