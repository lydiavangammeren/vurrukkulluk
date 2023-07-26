package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;

public interface ArticleUnitService {


    ArticleUnit save(ArticleUnit articleUnit);
    Unit getDefaultUnitFromArticleId(int articleId);
    ArticleUnit getDefaultUnitArticleFromArticleId(int articleId);

}
