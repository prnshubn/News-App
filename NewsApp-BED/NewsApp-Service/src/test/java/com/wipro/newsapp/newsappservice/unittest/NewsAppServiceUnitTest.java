package com.wipro.newsapp.newsappservice.unittest;

import com.wipro.newsapp.newsappservice.model.*;
import com.wipro.newsapp.newsappservice.service.NewsAppService;
import com.wipro.newsapp.newsappservice.service.RestTemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

;


@ExtendWith(MockitoExtension.class)
public class NewsAppServiceUnitTest {

    @Mock
    private RestTemplateService restTemplateService;

    @InjectMocks
    private NewsAppService newsAppService;

    private News news;

    private Editorial editorial;
    private FavEditorial favEditorial;


    @Test
    public void testGetAllEditorials() {
        String email = "test@test.com";
        String token = "test_token";

        ListRestResponseEdi expectedResponse = new ListRestResponseEdi();

        when(newsAppService.getAllEditorials(email, token)).thenReturn(expectedResponse);

        ListRestResponseEdi actualResponse = newsAppService.getAllEditorials(email, token);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetAllNews() {
        String email = "test@test.com";
        String token = "test_token";

        ListRestResponseNews expectedResponse = new ListRestResponseNews();


        when(restTemplateService.getAllFavouritesNews(email, token)).thenReturn(expectedResponse);

        ListRestResponseNews actualResponse = newsAppService.getAllNews(email, token);

        assertEquals(expectedResponse, actualResponse);
    }


    @Test
    public void deleteTest() {
        String token = "your-token";
        String title = "your-title";
        String email = "your-email";


        newsAppService.delete(token, title, email);

        Mockito.verify(restTemplateService, times(1)).deleteFavourite(token, title, email);
    }


    @Test
    public void addNews_shouldAddToFavorites() {
        // arrange
        News news = new News();
        news.setEmail("test@example.com");
        news.setTitle("Test News");

        String token = "test-token";


        newsAppService.addNews(news, token);

        Mockito.verify(restTemplateService).addFavourites(eq(token), any(Favourite.class));
    }


    @Test
    public void testAddEditorial() {
        FavEditorial favEditorial = new FavEditorial();
        favEditorial.setEmail("email@test.com");
        favEditorial.setTitle("Test Title");

        String token = "test-token";

        Favourite favourite = new Favourite();
        favourite.setEmail(favEditorial.getEmail());
        favourite.setTitle(favEditorial.getTitle());
        favourite.setContentType("editorial");


        newsAppService.addEditorial(favEditorial, token);

        Mockito.verify(restTemplateService).addFavourites(eq(token), any(Favourite.class));
    }

}
