package com.wipro.newsapp.adminservice.unittest;

import com.wipro.newsapp.adminservice.model.User;
import com.wipro.newsapp.adminservice.repository.UserRepository;
import com.wipro.newsapp.adminservice.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdminServiceImplUnitTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUser_id(1L);
        user1.setEmail("test1@example.com");

        User user2 = new User();
        user2.setUser_id(2L);
        user2.setEmail("test2@example.com");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = adminService.getAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(userList.size(), result.size());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUser_id(1L);
        user.setEmail("test@example.com");
        user.setActive(true);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        String result = adminService.deleteUser(user.getEmail());

        assertEquals("delete success", result);
    }

    @Test
    public void activateUser() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setActive(false);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = adminService.activateUser(email);

        assertTrue(user.getActive());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deactivateUser() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setActive(true);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = adminService.deactivateUser(email);

        assertFalse(user.getActive());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdatePassword() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        User foundUser = new User();
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(foundUser));
        when(userRepository.save(any(User.class))).thenReturn(foundUser);

        User updatedUser = adminService.updatePassword(user);

        assertNotNull(updatedUser);
        assertEquals(foundUser.getEmail(), updatedUser.getEmail());
        assertNotEquals(user.getPassword(), updatedUser.getPassword());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(foundUser);
    }

}



