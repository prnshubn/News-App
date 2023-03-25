package com.wipro.newsapp.adminservice.service;


import com.wipro.newsapp.adminservice.annotation.Generated;
import com.wipro.newsapp.adminservice.exception.UserAlreadyActiveException;
import com.wipro.newsapp.adminservice.model.User;
import com.wipro.newsapp.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Generated
    public void initRolesAndUsers() {

        User adminUser = new User();
        adminUser.setEmail("admin");
        adminUser.setName("admin");
        adminUser.setActive(true);
        adminUser.setCreatedOn(LocalDate.now());
        adminUser.setPassword(bCryptPasswordEncoder.encode("admin"));
        adminUser.setRole("admin");
        userRepository.save(adminUser);

        User user1 = new User();
        user1.setEmail("priyanshu");
        user1.setName("priyanshu");
        user1.setActive(true);
        user1.setCreatedOn(LocalDate.now());
        user1.setPassword(bCryptPasswordEncoder.encode("priyanshu"));
        user1.setRole("user");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("sohini");
        user2.setName("sohini");
        user2.setActive(true);
        user2.setCreatedOn(LocalDate.now());
        user2.setPassword(bCryptPasswordEncoder.encode("sohini"));
        user2.setRole("user");
        userRepository.save(user2);

        User user3 = new User();
        user3.setEmail("trishna");
        user3.setName("trishna");
        user3.setActive(true);
        user3.setCreatedOn(LocalDate.now());
        user3.setPassword(bCryptPasswordEncoder.encode("trishna"));
        user3.setRole("user");
        userRepository.save(user3);

        User user4 = new User();
        user4.setEmail("srinjit");
        user4.setName("srinjit");
        user4.setActive(true);
        user4.setCreatedOn(LocalDate.now());
        user4.setPassword(bCryptPasswordEncoder.encode("srinjit"));
        user4.setRole("user");
        userRepository.save(user4);

        User user5 = new User();
        user5.setEmail("debojita");
        user5.setName("debojita");
        user5.setActive(true);
        user5.setCreatedOn(LocalDate.now());
        user5.setPassword(bCryptPasswordEncoder.encode("debojita"));
        user5.setRole("user");
        userRepository.save(user5);

        User user6 = new User();
        user6.setEmail("keshaw");
        user6.setName("keshaw");
        user6.setActive(true);
        user6.setCreatedOn(LocalDate.now());
        user6.setPassword(bCryptPasswordEncoder.encode("keshaw"));
        user6.setRole("user");
        userRepository.save(user6);
    }

    @Override
    public String deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
        userRepository.delete(user);
        return "delete success";
    }

    @Override
    public User activateUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
        if (user.getActive() == true)
            throw new UserAlreadyActiveException("user already active");
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User deactivateUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
        if (user.getActive() == false)
            throw new UserAlreadyActiveException("user already inactive");
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user) {
        User u = userRepository.findByEmail(user.getEmail()).orElseThrow();
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
