package com.wipro.newsapp.userservice.service;

import com.wipro.newsapp.userservice.exception.LikeStatusErrorException;
import com.wipro.newsapp.userservice.exception.ReportAlreadyExistException;
import com.wipro.newsapp.userservice.exception.ReportNotFoundException;
import com.wipro.newsapp.userservice.model.Interaction;
import com.wipro.newsapp.userservice.model.ListRestResponse;
import com.wipro.newsapp.userservice.model.ListRestResponseAllNews;
import com.wipro.newsapp.userservice.repository.UserInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    private RestTemplateService restTemplateService;

    public Interaction addReport(String email, String title, String report, String token) {
        restTemplateService.authenticate(token);
        Interaction interaction = userInteractionRepository.findByEmailAndEdiTitle(email, title).orElse(null);
        if (interaction == null) {
            Interaction i = new Interaction();
            i.setEmail(email);
            i.setEdiTitle(title);
            i.setReport(report);
            return userInteractionRepository.save(i);
        } else if (interaction.getReport() == null) {
            interaction.setReport(report);
            return userInteractionRepository.save(interaction);
        } else throw new ReportAlreadyExistException("couldn't add report");
    }

    public List<Interaction> getReportByEdiTitle(String ediTitle, String token) {
        restTemplateService.authenticate(token);
        List<Interaction> reportList = userInteractionRepository.findByEdiTitle(ediTitle).orElseThrow();
        return reportList;
    }

    public Interaction addLikeStatus(String email, String title, String token) {
        restTemplateService.authenticate(token);
        Interaction interaction = userInteractionRepository.findByEmailAndEdiTitle(email, title).orElse(null);
        if (interaction == null) {
            Interaction i = new Interaction();
            i.setEmail(email);
            i.setEdiTitle(title);
            i.setLikeStatus(true);
            return userInteractionRepository.save(i);
        } else if (!interaction.getLikeStatus()) {
            interaction.setLikeStatus(true);
            return userInteractionRepository.save(interaction);
        } else throw new LikeStatusErrorException("error occured");
    }

    public String deleteReport(String email, String title, String token) {
        restTemplateService.authenticate(token);
        Interaction interaction = userInteractionRepository.findByEmailAndEdiTitle(email, title).orElseThrow();
        if (!interaction.getLikeStatus()) {
            userInteractionRepository.delete(interaction);
        } else {
            interaction.setReport(null);
            userInteractionRepository.save(interaction);
        }
        return "report deleted successfully";
    }

    public String deleteAllReportsByTitle(String ediTitle, String token) {
        restTemplateService.authenticate(token);
        userInteractionRepository.deleteByEdiTitle(ediTitle);
        return "reports deleted successfully";
    }

    public Interaction deleteLikeStatus(String email, String title, String token) {
        restTemplateService.authenticate(token);
        Interaction interaction = userInteractionRepository.findByEmailAndEdiTitle(email, title).orElseThrow();
        if (interaction.getReport() == null) {
            userInteractionRepository.delete(interaction);
            return interaction;
        } else {
            interaction.setLikeStatus(false);
            userInteractionRepository.save(interaction);
            return interaction;
        }
    }

    public ListRestResponse getAllEditorials(String token) {
        restTemplateService.authenticate(token);
        return restTemplateService.getAllEditorials(token);
    }

    public ListRestResponseAllNews getAllNews(String token) {
        restTemplateService.authenticate(token);
        return restTemplateService.getAllNews(token);
    }

    public ListRestResponseAllNews getAllSportsNews(String token) {
        restTemplateService.authenticate(token);
        return restTemplateService.getAllSportsNews(token);
    }

    public ListRestResponseAllNews getAllBusinessNews(String token) {
        restTemplateService.authenticate(token);
        return restTemplateService.getAllBusinessNews(token);
    }

    public ListRestResponseAllNews getAllSearchedNews(String key, String token) {
        restTemplateService.authenticate(token);
        return restTemplateService.getAllSearchNews(key, token);
    }
}
