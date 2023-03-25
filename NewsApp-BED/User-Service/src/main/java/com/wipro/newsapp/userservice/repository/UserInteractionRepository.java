package com.wipro.newsapp.userservice.repository;

import com.wipro.newsapp.userservice.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInteractionRepository extends JpaRepository<Interaction, Integer> {

    Optional<Interaction> findByEmailAndEdiTitle(String email, String title);

    Optional<List<Interaction>> findByEdiTitle(String title);

    void deleteByEdiTitle(String title);
}
