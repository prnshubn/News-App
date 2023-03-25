package com.wipro.newsapp.userauthentication.service;

import com.wipro.newsapp.userauthentication.exception.UserAlreadyExistsException;
import com.wipro.newsapp.userauthentication.exception.UserNotFoundException;
import com.wipro.newsapp.userauthentication.model.BlackListedToken;
import com.wipro.newsapp.userauthentication.model.User;
import com.wipro.newsapp.userauthentication.repository.BlackListedTokenRepository;
import com.wipro.newsapp.userauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final BlackListedTokenRepository blackListedTokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthenticationManager authenticationManager, BlackListedTokenRepository blackListedTokenRepository) {
        super();
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.blackListedTokenRepository = blackListedTokenRepository;
    }

    @Override
    public User signup(User user) {

        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent())
            throw new UserAlreadyExistsException("user with this email already exists");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCreatedOn(LocalDate.now());
        user.setRole("user");
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        try {
            User u = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user not found"));
            if (!u.getActive())
                throw new UserNotFoundException("user in inactive");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new UserNotFoundException("bad credentials!");
        }
        return userRepository.findByEmail(email).get();
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User updatePassword(User user) {
        User u = userRepository.findByEmail(user.getEmail()).orElseThrow();
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public String logoutUser(String token) {
        BlackListedToken blackListedToken = new BlackListedToken();
        blackListedToken.setToken(token);
        blackListedTokenRepository.save(blackListedToken);
        return "logout successful";
    }

    @Override
    public String getRole(String email) {
        User u = userRepository.findByEmail(email).orElseThrow();
        return u.getRole();
    }

    @Override
    public String getStatus(String email) {
        User u = userRepository.findByEmail(email).orElseThrow();
        if (u.getActive())
            return "active";
        return "inactive";
    }
}
