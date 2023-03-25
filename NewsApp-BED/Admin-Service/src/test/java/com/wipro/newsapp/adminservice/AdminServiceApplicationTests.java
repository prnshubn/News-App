package com.wipro.newsapp.adminservice;

import com.wipro.newsapp.adminservice.controller.AdminServiceController;
import com.wipro.newsapp.adminservice.service.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AdminServiceApplicationTests {

    @Autowired
    AdminServiceController adminController;

    @Autowired
    AdminServiceImpl adminServiceImpl;


    @Test
    void contextLoads() {
        assertThat(adminController).isNotNull();
        assertThat(adminServiceImpl).isNotNull();
    }

}
