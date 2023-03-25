package com.wipro.newsapp.userauthentication;

import com.wipro.newsapp.userauthentication.controller.UserAuthController;
import com.wipro.newsapp.userauthentication.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserAuthApplicationTests {

    @Autowired
    UserAuthController userController;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(userServiceImpl).isNotNull();
    }
}
