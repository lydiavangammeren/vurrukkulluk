package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    List<Article> findByName(String name);
}
