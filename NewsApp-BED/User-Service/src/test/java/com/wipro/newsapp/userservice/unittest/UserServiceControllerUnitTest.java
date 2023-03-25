package com.wipro.newsapp.userservice.unittest;

import com.wipro.newsapp.userservice.controller.UserServiceController;
import com.wipro.newsapp.userservice.exception.LikeStatusErrorException;
import com.wipro.newsapp.userservice.exception.ReportAlreadyExistException;
import com.wipro.newsapp.userservice.exception.ReportNotFoundException;
import com.wipro.newsapp.userservice.model.Interaction;
import com.wipro.newsapp.userservice.model.ListRestResponse;
import com.wipro.newsapp.userservice.model.ListRestResponseAllNews;
import com.wipro.newsapp.userservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceControllerUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserServiceController userServiceController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddReport() throws ReportAlreadyExistException {
        Interaction interaction = new Interaction();
        interaction.setEmail("test@test.com");
        interaction.setEdiTitle("testEdi");
        interaction.setReport("testReport");

        when(userService.addReport(interaction.getEmail(), interaction.getEdiTitle(), interaction.getReport(), "testToken"))
                .thenReturn(interaction);

        ResponseEntity<?> responseEntity = userServiceController.addReport(interaction, "testToken");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(interaction, responseEntity.getBody());
    }

    @Test
    public void addLikeTest() throws LikeStatusErrorException {
        Interaction interaction = new Interaction(1, "title", "user@example.com", "News Title", true);

        when(userService.addLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), "token"))
                .thenReturn(interaction);

        ResponseEntity<?> response = userServiceController.addLike(interaction, "token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(interaction, response.getBody());
    }

    @Test
    public void getReportsByEdiTitle_returnsReports_whenFound() throws ReportNotFoundException {
        Interaction interaction = new Interaction();
        interaction.setEmail("test@example.com");
        interaction.setEdiTitle("Test Editorial");
        interaction.setReport("Test Report");
        String token = "test-token";
        List<Interaction> report = new ArrayList<>();
        when(userService.getReportByEdiTitle(interaction.getEdiTitle(), token)).thenReturn(report);

        ResponseEntity<?> responseEntity = userServiceController.getReportsByEdiTitle(interaction.getEdiTitle(), token);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(report, responseEntity.getBody());
        Mockito.verify(userService, times(1)).getReportByEdiTitle(interaction.getEdiTitle(), token);
    }

    @Test
    public void getReportsByEdiTitle_returnsNotFound_whenNotFound() throws ReportNotFoundException {


        Interaction interaction = new Interaction();
        interaction.setEmail("test@example.com");
        interaction.setEdiTitle("Test Editorial");
        interaction.setReport("Test Report");
        String token = "test-token";
        List<Interaction> report = new ArrayList<>();
        when(userService.getReportByEdiTitle(interaction.getEdiTitle(), token)).thenThrow(ReportNotFoundException.class);
        ResponseEntity<?> responseEntity = userServiceController.getReportsByEdiTitle(interaction.getEdiTitle(), token);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No reports found", responseEntity.getBody());
        Mockito.verify(userService, times(1)).getReportByEdiTitle(interaction.getEdiTitle(), token);
    }

    @Test
    public void deleteLikeStatus_returnsOk_whenDeleted() throws LikeStatusErrorException {
        Interaction interaction = new Interaction();
        interaction.setEmail("test@example.com");
        interaction.setEdiTitle("Test Editorial");
        interaction.setReport("Test Report");
        String token = "test-token";
        List<Interaction> report = new ArrayList<>();
        when(userService.deleteLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token)).thenReturn(interaction);
        ResponseEntity<?> responseEntity = userServiceController.deleteLikeStatus(interaction, token);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(interaction, responseEntity.getBody());
        Mockito.verify(userService, times(1)).deleteLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token);
    }

    @Test
    public void deleteLikeStatus_returnsBadRequest_whenError() throws LikeStatusErrorException {
        Interaction interaction = new Interaction();
        interaction.setEmail("test@example.com");
        interaction.setEdiTitle("Test Editorial");
        interaction.setReport("Test Report");
        String token = "test-token";
        List<Interaction> report = new ArrayList<>();
        when(userService.deleteLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token)).thenThrow(LikeStatusErrorException.class);
        ResponseEntity<?> responseEntity = userServiceController.deleteLikeStatus(interaction, token);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("error occured", responseEntity.getBody());
        Mockito.verify(userService, times(1)).deleteLikeStatus(interaction.getEmail(), interaction.getEdiTitle(), token);
    }

    @Test
    public void getAllEditorials_success() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllEditorials(TOKEN)).thenReturn(new ListRestResponse());

        ResponseEntity<?> response = userServiceController.getAllEditorials(TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllEditorials_reportNotFound() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllEditorials(TOKEN)).thenThrow(new ReportNotFoundException("error occurred"));

        ResponseEntity<?> response = userServiceController.getAllEditorials(TOKEN);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error occurred", response.getBody());
    }

    @Test
    public void getAllNews_success() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllNews(TOKEN)).thenReturn(new ListRestResponseAllNews());

        ResponseEntity<?> response = userServiceController.getAllNews(TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllNews_reportNotFound() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllNews(TOKEN)).thenThrow(new ReportNotFoundException("error occurred"));

        ResponseEntity<?> response = userServiceController.getAllNews(TOKEN);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error occurred", response.getBody());
    }

    @Test
    public void getAllSportsNews_success() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllSportsNews(TOKEN)).thenReturn(new ListRestResponseAllNews());

        ResponseEntity<?> response = userServiceController.getAllSportsNews(TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllSportsNews_reportNotFound() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllSportsNews(TOKEN)).thenThrow(new ReportNotFoundException("error occurred"));

        ResponseEntity<?> response = userServiceController.getAllSportsNews(TOKEN);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error occurred", response.getBody());
    }

    @Test
    public void getAllBusinessNews_success() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllBusinessNews(TOKEN)).thenReturn(new ListRestResponseAllNews());

        ResponseEntity<?> response = userServiceController.getAllBusinessNews(TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllBusinessNews_reportNotFound() throws ReportNotFoundException {
        String TOKEN = "token123";
        when(userService.getAllBusinessNews(TOKEN)).thenThrow(new ReportNotFoundException("error occurred"));

        ResponseEntity<?> response = userServiceController.getAllBusinessNews(TOKEN);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error occurred", response.getBody());
    }

}
