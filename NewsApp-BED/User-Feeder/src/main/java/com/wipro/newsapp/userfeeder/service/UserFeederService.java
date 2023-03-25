package com.wipro.newsapp.userfeeder.service;

import com.wipro.newsapp.userfeeder.annotation.Generated;
import com.wipro.newsapp.userfeeder.exception.CustomException;
import com.wipro.newsapp.userfeeder.model.*;
import com.wipro.newsapp.userfeeder.repository.UserFeederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFeederService {
    @Autowired
    private UserFeederRepository userFeederRepository;

    @Autowired
    private RestTemplateService restTemplateService;

    public Favourite saveFavourites(Favourite favourite, String token) {
        restTemplateService.authenticate(token);
        Optional<Favourite> checkNews = userFeederRepository.findByEmailAndTitle(favourite.getEmail(), favourite.getTitle());
        if (checkNews.isPresent())
            return favourite;
        else {
            return userFeederRepository.save(favourite);
        }
    }

    public Favourite deleteFavourite(String token, String title, String email) {
        restTemplateService.authenticate(token);
        Favourite favourite = userFeederRepository.findByEmailAndTitle(email, title).orElse(null);
//        if (favourite == null) {
//            throw new CustomException("Favourite not found!");
//        }
        userFeederRepository.delete(favourite);
        return favourite;
    }


    @Generated
    public List<Editorial> getFavouritesEditorial(String email, String token) {
        restTemplateService.authenticate(token);
        ListRestResponseEdi allEditorialList = restTemplateService.getAllEditorials(token);
        List<Favourite> favList = getFavouriteEditorials(email, "editorial", token);
        List<Editorial> favEdiList = new ArrayList<>();
        for (int i = 0; i < favList.size(); i++) {
            for (int j = 0; j < allEditorialList.getList().size(); j++) {
                if (favList.get(i).getTitle().equals(allEditorialList.getList().get(j).getTitle())) {
                    favEdiList.add(allEditorialList.getList().get(j));
                    break;
                }
            }
        }
        return favEdiList;
    }

    @Generated
    public List<News> getFavouritesNews(String email, String token) {
        restTemplateService.authenticate(token);
        List<Favourite> favList = getFavouriteNews(email, "news", token);
        List<Favourite> favSportsList = new ArrayList<>();
        List<Favourite> favBusinessList = new ArrayList<>();
        List<News> favNewsList = new ArrayList<>();
        if (favList.size() > 0) {
            ListRestResponseNews allNewsList = restTemplateService.getAllNews(token);
            for (int i = 0; i < favList.size(); i++) {
                for (int j = 0; j < allNewsList.getList().size(); j++) {
                    if (favList.get(i).getTitle().equals(allNewsList.getList().get(j).getTitle())) {
                        favNewsList.add(allNewsList.getList().get(j));
                        break;
                    }
                }
            }
        }
        if(favList.size()>favNewsList.size()){
            favSportsList = getFavouriteNews(email, "news", token);
        }
        if (favSportsList.size() > 0) {
            ListRestResponseNews allNewsList = restTemplateService.getAllSportslNews(token);
            for (int i = 0; i < favSportsList.size(); i++) {
                for (int j = 0; j < allNewsList.getList().size(); j++) {
                    if (favSportsList.get(i).getTitle().equals(allNewsList.getList().get(j).getTitle())) {
                        favNewsList.add(allNewsList.getList().get(j));
                        break;
                    }
                }
            }
        }
        if(favList.size()>favNewsList.size()){
            favBusinessList = getFavouriteNews(email, "news", token);
        }
        if (favSportsList.size() > 0) {
            ListRestResponseNews allNewsList = restTemplateService.getAllBusinesslNews(token);
            for (int i = 0; i < favBusinessList.size(); i++) {
                for (int j = 0; j < allNewsList.getList().size(); j++) {
                    if (favBusinessList.get(i).getTitle().equals(allNewsList.getList().get(j).getTitle())) {
                        favNewsList.add(allNewsList.getList().get(j));
                        break;
                    }
                }
            }
        }
        return favNewsList;
    }

    public List<Favourite> getFavouriteNews(String email, String isNews, String token) {
        restTemplateService.authenticate(token);
        List<Favourite> favouritesList = (List<Favourite>) userFeederRepository.findByEmailAndIsNews(email, isNews).orElseThrow();
        return favouritesList;
    }

    public List<Favourite> getFavouriteEditorials(String email, String isNews, String token) {
        restTemplateService.authenticate(token);
        List<Favourite> favouritesList = (List<Favourite>) userFeederRepository.findByEmailAndIsNews(email, isNews).orElseThrow();
        return favouritesList;
    }
}