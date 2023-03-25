package com.wipro.newsapp.newsappservice.unittest;


import com.wipro.newsapp.newsappservice.controller.NewsAppController;
import com.wipro.newsapp.newsappservice.model.FavEditorial;
import com.wipro.newsapp.newsappservice.model.News;
import com.wipro.newsapp.newsappservice.service.NewsAppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NewsAppControllerUnitTest {

    @Mock
    private NewsAppService newsAppService;

    @InjectMocks
    private NewsAppController newsAppController;

    @Test
    public void addNewsTest() {
        News news = new News();
        String token = "token";

        ResponseEntity<?> response = newsAppController.addNews(news, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(news, response.getBody());

        verify(newsAppService).addNews(news, token);
    }

    @Test
    public void addEditorialTest() {
        FavEditorial favEditorial = new FavEditorial();
        String token = "token";

        ResponseEntity<?> response = newsAppController.addEditorial(favEditorial, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(favEditorial, response.getBody());

        verify(newsAppService).addEditorial(favEditorial, token);
    }

    @Test
    public void deleteTest() {
        String token = "token";
        String email = "email";
        String title = "title";

        ResponseEntity<?> response = newsAppController.delete(token, title, email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(newsAppService).delete(token, title, email);
    }

    @Test
    public void getAllNewsTest() {
        String email = "email";
        String token = "token";
        ResponseEntity<?> response = newsAppController.getAllNews(email, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(newsAppService).getAllNews(email, token);
    }

    @Test
    public void getAllEditorialsTest() {
        String email = "email";
        String token = "token";


        ResponseEntity<?> response = newsAppController.getAllEditorials(email, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(newsAppService).getAllEditorials(email, token);
    }

}

