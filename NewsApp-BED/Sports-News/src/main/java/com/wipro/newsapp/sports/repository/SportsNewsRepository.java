package com.wipro.newsapp.sports.repository;

import com.wipro.newsapp.sports.model.SportsNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsNewsRepository extends JpaRepository<SportsNews, String> {
}
