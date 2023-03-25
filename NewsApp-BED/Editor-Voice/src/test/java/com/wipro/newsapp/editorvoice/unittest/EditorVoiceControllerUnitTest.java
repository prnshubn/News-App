package com.wipro.newsapp.editorvoice.unittest;

import com.wipro.newsapp.editorvoice.controller.EditorVoiceController;
import com.wipro.newsapp.editorvoice.exception.EditorialAlreadyExistsException;
import com.wipro.newsapp.editorvoice.exception.EditorialNotFoundException;
import com.wipro.newsapp.editorvoice.model.Editorial;
import com.wipro.newsapp.editorvoice.model.Interaction;
import com.wipro.newsapp.editorvoice.model.ListRestResponse;
import com.wipro.newsapp.editorvoice.repository.InteractionRepository;
import com.wipro.newsapp.editorvoice.service.EditorVoiceService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditorVoiceControllerUnitTest {

    @Mock
    private EditorVoiceService editorVoiceService;

    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private EditorVoiceController editorVoiceController;

    @Autowired
    private ListRestResponse listRestResponse;

    @Test
    public void testAddEditorial() throws EditorialAlreadyExistsException {
        Editorial editorial = new Editorial();
        when(editorVoiceService.saveEditorial(eq(editorial), anyString())).thenReturn(editorial);

        ResponseEntity<?> response = editorVoiceController.addEditorial(editorial, "token");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(editorial, response.getBody());
    }

    @Test
    public void testAddEditorialConflict() throws EditorialAlreadyExistsException {
        Editorial editorial = new Editorial();
        when(editorVoiceService.saveEditorial(eq(editorial), anyString())).thenThrow(EditorialAlreadyExistsException.class);

        ResponseEntity<?> response = editorVoiceController.addEditorial(editorial, "token");

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("failed to create editorial", response.getBody());
    }

    @Test
    public void testGetAllEditorials() throws EditorialNotFoundException {
        String token = "abc123";
        List<Editorial> editorialList = new ArrayList<>();
        editorialList.add(new Editorial(1, "Title 1", "Body 1",false));
        when(editorVoiceService.getAllEditorials(anyString())).thenReturn(editorialList);

        ResponseEntity<?> response = editorVoiceController.getAllEditorials("token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllEditorialByTitle() throws EditorialNotFoundException {
        String title = "test";
        String token = "xyz";
        Editorial editorial = new Editorial();

        Mockito.when(editorVoiceService.getEditorialByTitle(Mockito.anyString(), Mockito.anyString())).thenReturn(editorial);

        ResponseEntity<?> responseEntity = editorVoiceController.getEditorialByTitle(title, token);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(editorial, responseEntity.getBody());
    }


    @Test
    public void testUpdateEditorial() {
        Editorial editorial = new Editorial();
        when(editorVoiceService.updateEditorial(eq(editorial), anyString())).thenReturn(editorial);

        ResponseEntity<?> response = editorVoiceController.updateEditorial(editorial, "token");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(editorial, response.getBody());
    }

    @Test
    void testDeleteEditorial() throws EditorialNotFoundException {
        String title = "Test Editorial";
        String token = "test-token";
        ResponseEntity<?> response = editorVoiceController.deleteEditorial(title, token);
        verify(editorVoiceService, times(1)).deleteEditorial(title, token);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("editorial deleted successfully!", response.getBody());
    }

    @Test
    void testDeleteEditorialNotFound() throws EditorialNotFoundException {
        String title = "Non-existent Editorial";
        String token = "test-token";
        doThrow(new EditorialNotFoundException("Editorial not found")).when(editorVoiceService).deleteEditorial(title, token);
        ResponseEntity<?> response = editorVoiceController.deleteEditorial(title, token);
        verify(editorVoiceService, times(1)).deleteEditorial(title, token);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Editorial not found", response.getBody());
    }



    @Test
    public void testViewReportEditorial() throws EditorialNotFoundException {
        String title = "Test Editorial";
        String token = "test_token";

        Map<String, List<Interaction>> map = new HashMap<>();
        when(editorVoiceService.viewReportEditorial(title, token)).thenReturn(map);

        ResponseEntity<?> responseEntity = editorVoiceController.viewReportEditorial(title, token);

        verify(editorVoiceService).viewReportEditorial(title, token);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testViewReportEditorialNotFound() {
        String title = "Title";
        String token = "Token";
        when(editorVoiceService.viewReportEditorial(anyString(), anyString())).thenThrow(new EditorialNotFoundException("Editorial not found"));

        ResponseEntity<?> expectedResponseEntity = new ResponseEntity<>("Editorial not found", HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponseEntity = editorVoiceController.viewReportEditorial(title, token);

        assertEquals(expectedResponseEntity, actualResponseEntity);
    }
}