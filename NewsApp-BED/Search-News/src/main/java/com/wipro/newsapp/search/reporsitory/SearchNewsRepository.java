package com.wipro.newsapp.search.reporsitory;

import com.wipro.newsapp.search.model.SearchNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchNewsRepository extends JpaRepository<SearchNews, String> {
}
