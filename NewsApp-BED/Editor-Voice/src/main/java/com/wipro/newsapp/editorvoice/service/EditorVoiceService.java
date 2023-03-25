package com.wipro.newsapp.editorvoice.service;

import com.wipro.newsapp.editorvoice.exception.EditorialAlreadyExistsException;
import com.wipro.newsapp.editorvoice.exception.EditorialNotFoundException;
import com.wipro.newsapp.editorvoice.model.Editorial;
import com.wipro.newsapp.editorvoice.model.Interaction;
import com.wipro.newsapp.editorvoice.repository.EditorVoiceRepository;
import com.wipro.newsapp.editorvoice.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EditorVoiceService {

    @Autowired
    private EditorVoiceRepository editorVoiceRepository;

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private InteractionRepository interactionRepository;

    public Editorial saveEditorial(Editorial edi, String token) {
        restTemplateService.authenticate(token);
        Optional<Editorial> checkEdi = editorVoiceRepository.findByTitle(edi.getTitle());
        if (checkEdi.isPresent())
            throw new EditorialAlreadyExistsException("editorial already exists!");
        else {
            edi.setNews(false);
            return editorVoiceRepository.save(edi);
        }
    }

    public boolean deleteEditorial(String title, String token) {
        restTemplateService.authenticate(token);
        Editorial edi = editorVoiceRepository.findByTitle(title).orElse(null);
        if (edi == null) {
            throw new EditorialNotFoundException("editorial not found!");
        }
        editorVoiceRepository.delete(edi);
        return true;
    }

    public List<Editorial> getAllEditorials(String token) {
        restTemplateService.authenticate(token);
        List<Editorial> ediList = editorVoiceRepository.findAll();
        if (ediList.size() == 0)
            throw new EditorialNotFoundException("no editorials found");
        return ediList;
    }

    public Editorial getEditorialByTitle(String title, String token){
        restTemplateService.authenticate(token);
        Editorial editorial = editorVoiceRepository.findByTitle(title).orElseThrow();
        return  editorial;
    }

    public Editorial updateEditorial(Editorial edi, String token) {
        restTemplateService.authenticate(token);
        Editorial e = editorVoiceRepository.findByTitle(edi.getTitle()).orElse(null);
        if (e == null) {
            throw new EditorialNotFoundException("editorial not found!");
        }
        e.setBody(edi.getBody());
        editorVoiceRepository.save(e);
        return e;
    }

    public Map<String, List<Interaction>> viewReportEditorial(String ediTitle, String token) {
        restTemplateService.authenticate(token);
        List<Interaction> reportList = restTemplateService.getReportsByEdiTitle(ediTitle, token);
        Editorial edi = editorVoiceRepository.findByTitle(ediTitle).orElse(null);
        if (edi == null)
            throw new EditorialNotFoundException("editorial not found!");
//        List<Interaction> list = interactionRepository.findByEdiTitle(title);
        Map<String, List<Interaction>> map = new HashMap<>();
        map.put(edi.getTitle(), reportList);
        return map;
    }

//    public String deleteReport(String ediTitle, String token) {
//        restTemplateService.authenticate(token);
//        Editorial edi = editorVoiceRepository.findByTitle(ediTitle).orElse(null);
//        if (edi == null)
//            throw new EditorialNotFoundException("editorial not found!");
//        restTemplateService.deleteAllReportsByEdiTitle(ediTitle, token);
//        return "reports successfully deleted";
//    }
}
