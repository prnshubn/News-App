package com.wipro.newsapp.userfeeder.unittest;


import com.wipro.newsapp.userfeeder.exception.CustomException;
import com.wipro.newsapp.userfeeder.model.Favourite;
import com.wipro.newsapp.userfeeder.model.ListRestResponseEdi;
import com.wipro.newsapp.userfeeder.model.ListRestResponseNews;
import com.wipro.newsapp.userfeeder.repository.UserFeederRepository;
import com.wipro.newsapp.userfeeder.service.RestTemplateService;
import com.wipro.newsapp.userfeeder.service.UserFeederService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserFeederServiceUnitTest {

    @Mock
    private UserFeederRepository userFeederRepository;

    @Mock
    private RestTemplateService restTemplateService;

    @InjectMocks
    private UserFeederService userFeederService;

    private Favourite favourite;
    private List<Favourite> favouritesList;
    private ListRestResponseEdi allEditorialList;
    private ListRestResponseNews allNewsList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFavourites_Success() {
        Favourite favourite = new Favourite(1, "test@email.com", "test title", "news", "news");

        when(userFeederRepository.findByEmailAndTitle(favourite.getEmail(), favourite.getTitle()))
                .thenReturn(Optional.empty());
        when(userFeederRepository.save(favourite))
                .thenReturn(favourite);

        Favourite result = userFeederService.saveFavourites(favourite, "test token");

        assertEquals(favourite.getEmail(), result.getEmail());
        assertEquals(favourite.getTitle(), result.getTitle());

        verify(restTemplateService, times(1)).authenticate(anyString());
        verify(userFeederRepository, times(1))
                .findByEmailAndTitle(favourite.getEmail(), favourite.getTitle());
        verify(userFeederRepository, times(1)).save(favourite);
        verifyNoMoreInteractions(userFeederRepository);
    }

    @Test
    void deleteFavourite_Success() {
        Favourite favourite = new Favourite(1, "test@email.com", "test title", "news", "news");

        when(userFeederRepository.findByEmailAndTitle(favourite.getEmail(), favourite.getTitle()))
                .thenReturn(Optional.of(favourite));

        Favourite result = userFeederService.deleteFavourite("test token",favourite.getTitle(), favourite.getEmail());

        assertEquals(result,favourite);

        verify(restTemplateService, times(1)).authenticate(anyString());
        verify(userFeederRepository, times(1))
                .findByEmailAndTitle(favourite.getEmail(), favourite.getTitle());
        verify(userFeederRepository, times(1)).delete(favourite);
        verifyNoMoreInteractions(userFeederRepository);
    }


    @Test
    public void testGetFavouritesNews() {
        String email = "test@test.com";
        String isNews = "news";
        String token = "12345";

        List<Favourite> favouritesList = new ArrayList<>();
        Favourite favourite1 = new Favourite(1, "email", "title1", "news", "news");
        Favourite favourite2 = new Favourite(2, "email1", "title2", "news", "news");
        favouritesList.add(favourite1);
        favouritesList.add(favourite2);
        Mockito.when(userFeederRepository.findByEmailAndIsNews(email, isNews)).thenReturn(Optional.of(favouritesList));

        List<Favourite> result = userFeederService.getFavouriteNews(email,isNews, token);

        Mockito.verify(userFeederRepository, Mockito.times(1)).findByEmailAndIsNews(email, isNews);
        Mockito.verify(restTemplateService, Mockito.times(1)).authenticate(token);

        assertEquals(favouritesList, result);
    }

    @Test
    public void testGetFavouritesEditorials() {
        String email = "test@test.com";
        String isNews = "news";
        String token = "12345";

        List<Favourite> favouritesList = new ArrayList<>();
        Favourite favourite1 = new Favourite(1, "email", "title1", "editorial", "editorial");

        Favourite favourite2 = new Favourite(2, "email1", "title2", "editorial","editorial");

        favouritesList.add(favourite1);
        favouritesList.add(favourite2);
        Mockito.when(userFeederRepository.findByEmailAndIsNews(email, isNews)).thenReturn(Optional.of(favouritesList));

        List<Favourite> result = userFeederService.getFavouriteEditorials(email, isNews, token);

        Mockito.verify(userFeederRepository, Mockito.times(1)).findByEmailAndIsNews(email, isNews);
        Mockito.verify(restTemplateService, Mockito.times(1)).authenticate(token);

        assertEquals(favouritesList, result);
    }

}






