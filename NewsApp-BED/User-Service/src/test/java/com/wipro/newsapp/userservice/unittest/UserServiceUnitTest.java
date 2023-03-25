package com.wipro.newsapp.userservice.unittest;


import com.wipro.newsapp.userservice.exception.ReportAlreadyExistException;
import com.wipro.newsapp.userservice.exception.ReportNotFoundException;
import com.wipro.newsapp.userservice.model.Interaction;
import com.wipro.newsapp.userservice.model.ListRestResponse;
import com.wipro.newsapp.userservice.model.ListRestResponseAllNews;
import com.wipro.newsapp.userservice.repository.UserInteractionRepository;
import com.wipro.newsapp.userservice.service.RestTemplateService;
import com.wipro.newsapp.userservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

    @Mock
    UserInteractionRepository userInteractionRepository;
    @Mock
    RestTemplateService restTemplateService;
    @InjectMocks
    private UserService userService;

    @Test
    public void addReportTest() {
        String email = "test@test.com";
        String title = "test title";
        String report = "test report";
        String token = "testtoken";
        Interaction interaction = new Interaction();
        interaction.setEmail(email);
        interaction.setEdiTitle(title);

        Mockito.when(restTemplateService.authenticate(token)).thenReturn(String.valueOf(true));
        Mockito.when(userInteractionRepository.findByEmailAndEdiTitle(email, title)).thenReturn(Optional.empty());

        Interaction response = userService.addReport(email, title, report, token);

    }

    @Test
    public void addReportAlreadyExistTest() {
        String email = "test@test.com";
        String title = "test title";
        String report = "test report";
        String token = "testtoken";
        Interaction interaction = new Interaction();
        interaction.setEmail(email);
        interaction.setEdiTitle(title);
        interaction.setReport("test existing report");

        Mockito.when(restTemplateService.authenticate(token)).thenReturn(String.valueOf(true));
        Mockito.when(userInteractionRepository.findByEmailAndEdiTitle(email, title)).thenReturn(Optional.of(interaction));

        try {
            userService.addReport(email, title, report, token);
        } catch (ReportAlreadyExistException e) {
            Assert.assertEquals("couldn't add report", e.getMessage());
        }
    }

    @Test
    public void getReportByEdiTitleTest() {
        String ediTitle = "test title";
        String token = "testtoken";
        List<Interaction> reportList = new ArrayList<>();
        Interaction interaction1 = new Interaction();
        Interaction interaction2 = new Interaction();
        reportList.add(interaction1);
        reportList.add(interaction2);

        Mockito.when(restTemplateService.authenticate(token)).thenReturn(String.valueOf(true));
        Mockito.when(userInteractionRepository.findByEdiTitle(ediTitle)).thenReturn(Optional.of(reportList));

        List<Interaction> response = userService.getReportByEdiTitle(ediTitle, token);

        Assert.assertEquals(reportList, response);
    }

    @Test
    public void testDeleteAllReportsByTitle() {
        String ediTitle = "test editorial";
        String token = "validtoken";

        String result = userService.deleteAllReportsByTitle(ediTitle, token);

        verify(restTemplateService, Mockito.times(1)).authenticate(token);
        verify(userInteractionRepository, Mockito.times(1)).deleteByEdiTitle(ediTitle);
        assertEquals("reports deleted successfully", result);
    }


    @Test
    public void testDeleteReportWithLikeStatus() {
        String email = "testuser1@example.com";
        String title = "test editorial";
        String token = "validtoken";

        Interaction interaction = new Interaction();
        interaction.setEmail(email);
        interaction.setEdiTitle(title);
        interaction.setReport("test report");
        interaction.setLikeStatus(true);

        Mockito.when(userInteractionRepository.findByEmailAndEdiTitle(email, title)).thenReturn(Optional.of(interaction));

        String result = userService.deleteReport(email, title, token);

        verify(restTemplateService, Mockito.times(1)).authenticate(token);
        verify(userInteractionRepository, Mockito.times(1)).findByEmailAndEdiTitle(email, title);
        verify(userInteractionRepository, Mockito.times(1)).save(interaction);
        assertNull(interaction.getReport());
        assertEquals("report deleted successfully", result);
    }

    @Test
    public void testGetAllEditorials() {
        String token = "test_token";
        ListRestResponse expectedResponse = new ListRestResponse();

        Mockito.when(restTemplateService.authenticate(token)).thenReturn(null);
        Mockito.when(restTemplateService.getAllEditorials(token)).thenReturn(expectedResponse);

        ListRestResponse result = userService.getAllEditorials(token);

        verify(restTemplateService).authenticate(token);
        verify(restTemplateService).getAllEditorials(token);
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testGetAllNews() {
        String token = "test_token";
        Mockito.when(restTemplateService.authenticate("test-token")).thenReturn(String.valueOf(true));
        ListRestResponseAllNews expected = userService.getAllNews(token);
        Mockito.when(restTemplateService.getAllNews("test-token")).thenReturn(expected);

        ListRestResponseAllNews result = userService.getAllNews("test-token");

        assertEquals(expected, result);
    }

    @Test
    public void testGetAllSportsNews() {
        Mockito.when(restTemplateService.authenticate("test-token")).thenReturn(String.valueOf(true));
        ListRestResponseAllNews expected = new ListRestResponseAllNews();
        Mockito.when(restTemplateService.getAllSportsNews("test-token")).thenReturn(expected);

        ListRestResponseAllNews result = userService.getAllSportsNews("test-token");

        assertEquals(expected, result);
    }

    @Test
    public void testGetAllBusinessNews() {
        String token = "test_token";
        Mockito.when(restTemplateService.authenticate("test-token")).thenReturn(String.valueOf(true));
        ListRestResponseAllNews expected = new ListRestResponseAllNews();
        Mockito.when(restTemplateService.getAllBusinessNews("test-token")).thenReturn(expected);

        ListRestResponseAllNews result = userService.getAllBusinessNews("test-token");

        assertEquals(expected, result);
    }
}
