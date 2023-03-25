package com.wipro.newsapp.editorvoice.repository;

import com.wipro.newsapp.editorvoice.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Integer> {

    List<Interaction> findByEdiTitle(String title);

    String deleteByEdiTitle(String title);
}
