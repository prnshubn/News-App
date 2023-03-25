package com.wipro.newsapp.userfeeder;

import com.wipro.newsapp.userfeeder.controller.UserFeederController;
import com.wipro.newsapp.userfeeder.service.UserFeederService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserFeederApplicationTests {

    @Autowired
    private UserFeederService userFeederService;

    @Autowired
    private UserFeederController userFeederController;

    @Test
    void contextLoads() {
        assertThat(userFeederController).isNotNull();
        assertThat(userFeederService).isNotNull();
    }

}
