package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.model.Unit;

import java.util.List;

public interface ArticleUnitService {


    ArticleUnit save(ArticleUnit articleUnit);
    Unit getDefaultUnitFromArticleId(int articleId);
    ArticleUnit getDefaultUnitArticleFromArticleId(int articleId);

    List<ArticleUnit> getAll();
}
