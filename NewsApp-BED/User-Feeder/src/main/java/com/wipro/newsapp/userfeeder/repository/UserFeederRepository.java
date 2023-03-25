package com.wipro.newsapp.userfeeder.repository;

import com.wipro.newsapp.userfeeder.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFeederRepository extends JpaRepository<Favourite, Integer> {

    Optional<Favourite> findByEmailAndTitle(String email, String title);

    List<Favourite> findByEmail(String email);

    Optional<List<Favourite>> findByEmailAndIsNews(String email, String isNews);
}

