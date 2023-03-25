package com.wipro.newsapp.newsappservice.service;

import com.wipro.newsapp.newsappservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsAppService {

    @Autowired
    public RestTemplateService restTemplateService;

    public void addNews(News news, String token) {
        Favourite favourite = new Favourite();
        favourite.setEmail(news.getEmail());
        favourite.setTitle(news.getTitle());
        favourite.setContentType("topnews");
        favourite.setIsNews("news");
        restTemplateService.addFavourites(token, favourite);
    }

    public void addSportsNews(News news, String token) {
        Favourite favourite = new Favourite();
        favourite.setEmail(news.getEmail());
        favourite.setTitle(news.getTitle());
        favourite.setContentType("sportsnews");
        favourite.setIsNews("news");
        restTemplateService.addFavourites(token, favourite);
    }

    public void addBusinessNews(News news, String token) {
        Favourite favourite = new Favourite();
        favourite.setEmail(news.getEmail());
        favourite.setTitle(news.getTitle());
        favourite.setContentType("businessnews");
        favourite.setIsNews("news");
        restTemplateService.addFavourites(token, favourite);
    }

    public void addSearchedNews(News news, String token) {
        Favourite favourite = new Favourite();
        favourite.setEmail(news.getEmail());
        favourite.setTitle(news.getTitle());
        favourite.setContentType("searchednews");
        favourite.setIsNews("news");
        restTemplateService.addFavourites(token, favourite);
    }

    public void addEditorial(FavEditorial favEditorial, String token) {
        Favourite favourite = new Favourite();
        favourite.setEmail(favEditorial.getEmail());
        favourite.setTitle(favEditorial.getTitle());
        favourite.setContentType("editorial");
        favourite.setIsNews("editorial");
        restTemplateService.addFavourites(token, favourite);
    }

    public Favourite delete(String token, String title, String email) {
        return restTemplateService.deleteFavourite(token, title, email);
    }

    public ListRestResponseNews getAllNews(String email, String token) {
        return restTemplateService.getAllFavouritesNews(email, token);
    }

    public ListRestResponseEdi getAllEditorials(String email, String token) {
        return restTemplateService.getAllFavouritesEditorial(email, token, "editorial");
    }
}
