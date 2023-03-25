package com.wipro.newsapp.editorvoice.unittest;

import com.wipro.newsapp.editorvoice.controller.EditorVoiceController;
import com.wipro.newsapp.editorvoice.exception.EditorialAlreadyExistsException;
import com.wipro.newsapp.editorvoice.exception.EditorialNotFoundException;
import com.wipro.newsapp.editorvoice.model.Editorial;
import com.wipro.newsapp.editorvoice.model.Interaction;
import com.wipro.newsapp.editorvoice.model.ListRestResponse;
import com.wipro.newsapp.editorvoice.repository.EditorVoiceRepository;
import com.wipro.newsapp.editorvoice.repository.InteractionRepository;
import com.wipro.newsapp.editorvoice.service.EditorVoiceService;
import com.wipro.newsapp.editorvoice.service.RestTemplateService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static com.ctc.wstx.shaded.msv_core.datatype.xsd.NumberType.save;
import static org.bouncycastle.math.ec.rfc8032.Ed25519.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static reactor.core.Disposables.never;

class EditorVoiceServiceUnitTest {


    @InjectMocks
    private EditorVoiceService editorVoiceService;

    @Mock
    private EditorVoiceRepository editorVoiceRepository;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private InteractionRepository interactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveEditorialTest() {
        Editorial editorial = new Editorial();
        editorial.setTitle("Test Title");
        editorial.setBody("Test Body");

        when(editorVoiceRepository.findByTitle(any())).thenReturn(Optional.empty());
        when(editorVoiceRepository.save(any())).thenReturn(editorial);

        Editorial savedEditorial = editorVoiceService.saveEditorial(editorial, "token");

        Mockito.verify(editorVoiceRepository, times(1)).findByTitle("Test Title");
        Mockito.verify(editorVoiceRepository, times(1)).save(any());

        Assertions.assertEquals(editorial, savedEditorial);
    }

    @Test
    public void saveEditorialAlreadyExistsTest() {
        Editorial editorial = new Editorial();
        editorial.setTitle("Test Title");
        editorial.setBody("Test Body");

        when(editorVoiceRepository.findByTitle(any())).thenReturn(Optional.of(editorial));

        Assertions.assertThrows(EditorialAlreadyExistsException.class, () -> {
            editorVoiceService.saveEditorial(editorial, "token");
        });

        Mockito.verify(editorVoiceRepository, times(1)).findByTitle("Test Title");
        //Mockito.verify(editorVoiceRepository, never()).save(any());
    }

    @Test
    public void deleteEditorialTest() {
        Editorial editorial = new Editorial();
        editorial.setTitle("Test Title");
        editorial.setBody("Test Body");

        when(editorVoiceRepository.findByTitle(any())).thenReturn(Optional.of(editorial));
        //doNothing().when(editorVoiceRepository).delete(any());

        boolean deleted = editorVoiceService.deleteEditorial("Test Title", "token");

        Mockito.verify(editorVoiceRepository, times(1)).findByTitle("Test Title");
        Mockito.verify(editorVoiceRepository, times(1)).delete(any());

        Assertions.assertTrue(deleted);
    }

    @Test
    public void deleteEditorialNotFoundTest() {
        when(editorVoiceRepository.findByTitle(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EditorialNotFoundException.class, () -> {
            editorVoiceService.deleteEditorial("Test Title", "token");
        });

        Mockito.verify(editorVoiceRepository, times(1)).findByTitle("Test Title");
        //Mockito.verify(editorVoiceRepository, never()).delete(any());
    }

    @Test
    public void testGetAllEditorials() {
        List<Editorial> expectedEditorials = new ArrayList<>();
        expectedEditorials.add(new Editorial(1,"Title 1", "Body 1",false));
        expectedEditorials.add(new Editorial(2,"Title 2", "Body 2",false));
        when(editorVoiceRepository.findAll()).thenReturn(expectedEditorials);

        List<Editorial> actualEditorials = editorVoiceService.getAllEditorials("token");

        assertEquals(expectedEditorials, actualEditorials);
    }


    @Test
    public void testGetEditorialByTitle() {
        Editorial expectedEditorial = new Editorial(1,"Title", "Body",false);
        when(editorVoiceRepository.findByTitle(anyString())).thenReturn(Optional.of(expectedEditorial));

        Editorial actualEditorial = editorVoiceService.getEditorialByTitle("Title", "token");

        assertEquals(expectedEditorial, actualEditorial);
    }



    @Test
    public void testUpdateEditorial() {
        Editorial existingEditorial = new Editorial(1,"Title", "Body",false);
        Editorial updatedEditorial = new Editorial(2,"Title", "New Body",false);
        when(editorVoiceRepository.findByTitle(anyString())).thenReturn(Optional.of(existingEditorial));
        when(editorVoiceRepository.save(existingEditorial)).thenReturn(updatedEditorial);

        Editorial actualEditorial = editorVoiceService.updateEditorial(updatedEditorial, "token");

        assertEquals(updatedEditorial.getTitle(), actualEditorial.getTitle());
    }

    @Test
    public void testViewReportEditorial() {


         String ediTitle;
         String token;
         Editorial editorial;
         List<Interaction> reportList;
        ediTitle = "Test Editorial";
        token = "Test Token";
        editorial = new Editorial();
        editorial.setTitle(ediTitle);
        reportList = new ArrayList<>();
        reportList.add(new Interaction());
        when(editorVoiceRepository.findByTitle(ediTitle)).thenReturn(Optional.of(editorial));
        when(restTemplateService.getReportsByEdiTitle(ediTitle, token)).thenReturn(reportList);

        Map<String, List<Interaction>> map = editorVoiceService.viewReportEditorial(ediTitle, token);

        assertNotNull(map);
        assertEquals(1, map.get(ediTitle).size());
    }


}



