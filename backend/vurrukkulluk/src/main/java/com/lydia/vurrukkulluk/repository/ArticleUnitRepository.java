package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleUnitRepository extends JpaRepository<ArticleUnit,Integer> {

    @Query("SELECT au.defUnit FROM ArticleUnit au WHERE au.article.id=?1 GROUP BY au.article.id")
    Unit getDefaultUnitFromArticleId(int articleId);
}
