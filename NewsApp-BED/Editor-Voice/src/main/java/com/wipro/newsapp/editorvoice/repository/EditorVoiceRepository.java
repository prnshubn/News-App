package com.wipro.newsapp.editorvoice.repository;

import com.wipro.newsapp.editorvoice.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorVoiceRepository extends JpaRepository<Editorial, Integer> {
    Optional<Editorial> findByTitle(String title);

}
