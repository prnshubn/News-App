package com.wipro.newsapp.editorvoice.unittest;


import com.wipro.newsapp.editorvoice.model.Editorial;
import com.wipro.newsapp.editorvoice.repository.EditorVoiceRepository;
import com.wipro.newsapp.editorvoice.service.EditorVoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditorVoiceRepositoryUnitTest {

    @Mock
    private EditorVoiceRepository editorVoiceRepository;

    @InjectMocks
    private EditorVoiceService editorVoiceService;

    @Test
    public void testFindByTitle() {
        String title = "Example title";
        Editorial expected = new Editorial();
        expected.setTitle(title);
        when(editorVoiceRepository.findByTitle(title)).thenReturn(Optional.of(expected));

        Optional<Editorial> actual = editorVoiceRepository.findByTitle(title);

        assertEquals(expected, actual.get());
    }
}

