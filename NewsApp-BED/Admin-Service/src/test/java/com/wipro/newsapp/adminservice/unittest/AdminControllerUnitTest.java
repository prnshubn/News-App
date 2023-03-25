package com.wipro.newsapp.adminservice.unittest;

import com.wipro.newsapp.adminservice.controller.AdminServiceController;
import com.wipro.newsapp.adminservice.model.User;
import com.wipro.newsapp.adminservice.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class AdminControllerUnitTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminServiceController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUser() {
        String email = "test@example.com";
        when(adminService.deleteUser(email)).thenReturn("User deleted successfully");

        ResponseEntity<String> responseEntity = adminController.deleteUser(email);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        users.add(user);

        when(adminService.getAllUsers()).thenReturn(users);

        List<User> responseList = adminController.getAllUsers();

        assertEquals(users.size(), responseList.size());
        assertEquals(users.get(0).getName(), responseList.get(0).getName());
        assertEquals(users.get(0).getEmail(), responseList.get(0).getEmail());
    }

    @Test
    public void testActivateUserSuccess() {
        User user = new User();
        String email = "test@example.com";
        when(adminService.activateUser(email)).thenReturn(user);

        ResponseEntity<?> response = adminController.activateUser(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testDeactivateUserSuccess() {
        User user = new User();
        String email = "test@example.com";
        when(adminService.deactivateUser(email)).thenReturn(user);

        ResponseEntity<?> response = adminController.deactivateUser(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdatePassword() {
        User user = new User();
        when(adminService.updatePassword(user)).thenReturn(user);

        ResponseEntity<?> responseEntity = adminController.updatePassword(user);

        Mockito.verify(adminService, times(1)).updatePassword(user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }
}
