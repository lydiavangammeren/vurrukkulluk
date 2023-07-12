package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;

public interface ArticleUnitService {


    ArticleUnit save(ArticleUnit articleUnit);
    String getDefaultUnitFromArticleId(int articleId);

}
