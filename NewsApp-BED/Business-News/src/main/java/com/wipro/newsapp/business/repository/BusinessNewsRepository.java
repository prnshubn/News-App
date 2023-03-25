package com.wipro.newsapp.business.repository;

import com.wipro.newsapp.business.model.BusinessNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessNewsRepository extends JpaRepository<BusinessNews, String> {
}
