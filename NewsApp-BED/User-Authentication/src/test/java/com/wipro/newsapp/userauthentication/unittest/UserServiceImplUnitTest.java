package com.wipro.newsapp.userauthentication.unittest;

import com.wipro.newsapp.userauthentication.exception.UserAlreadyExistsException;
import com.wipro.newsapp.userauthentication.exception.UserNotFoundException;
import com.wipro.newsapp.userauthentication.model.BlackListedToken;
import com.wipro.newsapp.userauthentication.model.User;
import com.wipro.newsapp.userauthentication.repository.BlackListedTokenRepository;
import com.wipro.newsapp.userauthentication.repository.UserRepository;
import com.wipro.newsapp.userauthentication.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplUnitTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BlackListedTokenRepository blackListedTokenRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        authenticationManager = mock(AuthenticationManager.class);
        blackListedTokenRepository = mock(BlackListedTokenRepository.class);
        userServiceImpl = new UserServiceImpl(userRepository, bCryptPasswordEncoder, authenticationManager,
                blackListedTokenRepository);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup() throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("user");
        user.setActive(true);
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encryptedpassword");

        User savedUser = new User();
        savedUser.setUser_id(1L);
        when(userRepository.save(user)).thenReturn(savedUser);
        User returnedUser = userServiceImpl.signup(user);
        assertEquals(savedUser.getUser_id(), returnedUser.getUser_id());
    }

    @Test
    void testLogin() {
        String email = "test@gmail.com";
        String password = "password";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(true);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password))).thenReturn(null);
        User result = userServiceImpl.login(email, password);
        assertEquals(user, result);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        when(authenticationManager.authenticate(any())).thenThrow(new UserNotFoundException("bad credentials!"));
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.login("test@gmail.com", "password"));
    }

    @Test
    public void testDeleteUser() {
        userServiceImpl.deleteUser("test@gmail.com");
    }

    @Test
    void testLogoutUser() {
        String token = "someTokenValue";
        userServiceImpl.logoutUser(token);
        BlackListedToken blackListedToken = new BlackListedToken();
        blackListedToken.setToken_id(1L);
        blackListedToken.setToken(token);
        String result = userServiceImpl.logoutUser(token);
        assertEquals("logout successful", result);
    }

    @Test
    public void testUpdatePassword() {

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("oldpassword");

        User updatedUser = new User();
        updatedUser.setEmail("test@example.com");
        updatedUser.setPassword("newpassword");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.encode("newpassword")).thenReturn("encodednewpassword");
        when(userRepository.save(user)).thenReturn(updatedUser);

        User result = userServiceImpl.updatePassword(updatedUser);

        assertEquals("test@example.com", result.getEmail());
        assertEquals("newpassword", result.getPassword());

    }
}