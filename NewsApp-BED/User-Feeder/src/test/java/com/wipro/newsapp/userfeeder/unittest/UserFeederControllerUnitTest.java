package com.wipro.newsapp.userfeeder.unittest;

import com.wipro.newsapp.userfeeder.controller.UserFeederController;
import com.wipro.newsapp.userfeeder.model.*;
import com.wipro.newsapp.userfeeder.service.UserFeederService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFeederControllerUnitTest {

    @Mock
    private UserFeederService userFeederService;

    @InjectMocks
    private UserFeederController userFeederController;

    @Test
    public void testSaveFavourites() throws Exception {
        Favourite favourite = new Favourite();
        String token = "test_token";

        ResponseEntity<?> responseEntity = userFeederController.saveFavourites(favourite, token);

        verify(userFeederService).saveFavourites(favourite, token);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavourite() throws Exception {
        String email = "test@test.com";
        String title = "test_title";
        String token = "test_token";

        ResponseEntity<?> responseEntity = userFeederController.deleteFavourite(email, title, token);

        verify(userFeederService).deleteFavourite(email, title, token);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetMyFavouriteEditorials() throws Exception {
        String email = "test@test.com";
        String token = "test_token";
        List<Editorial> editorialList = new ArrayList<>();
        editorialList.add(new Editorial());

        when(userFeederService.getFavouritesEditorial(email, token)).thenReturn(editorialList);

        ResponseEntity<?> responseEntity = userFeederController.getMyFavouriteEditorials(email, token);

        verify(userFeederService).getFavouritesEditorial(email, token);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ListRestResponseEdi listRestResponseEdi = (ListRestResponseEdi) responseEntity.getBody();
        assertEquals(editorialList, listRestResponseEdi.getList());
    }

    @Test
    public void testGetMyFavouriteNews() throws Exception {
        String email = "test@test.com";
        String token = "test_token";
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());

        when(userFeederService.getFavouritesNews(email, token)).thenReturn(newsList);

        ResponseEntity<?> responseEntity = userFeederController.getMyFavouriteNews(email, token);

        verify(userFeederService).getFavouritesNews(email, token);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ListRestResponseNews listRestResponseNews = (ListRestResponseNews) responseEntity.getBody();
        assertEquals(newsList, listRestResponseNews.getList());
    }
}
