package com.wipro.newsapp.top.repository;

import com.wipro.newsapp.top.model.TopNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopNewsRepository extends JpaRepository<TopNews, String> {
}
