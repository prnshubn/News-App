package com.wipro.newsapp.adminservice.service;

import com.wipro.newsapp.adminservice.model.User;

import java.util.List;

public interface AdminService {
    String deleteUser(String email);

    List<User> getAllUsers();

    User activateUser(String email);

    User deactivateUser(String email);

    void initRolesAndUsers();

    User updatePassword(User user);

}
