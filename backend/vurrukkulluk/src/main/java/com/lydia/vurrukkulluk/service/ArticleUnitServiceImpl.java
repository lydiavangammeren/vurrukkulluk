package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.repository.ArticleUnitRepository;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleUnitServiceImpl implements ArticleUnitService{

    @Autowired
    private ArticleUnitRepository articleUnitRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public ArticleUnit save(ArticleUnit articleUnit) {
        articleUnitRepository.save(articleUnit);
        return articleUnit;
    }

    @Override
    public String getDefaultUnitFromArticleId(int articleId) {


        return articleUnitRepository.getDefaultUnitFromArticleId(articleId).getName();
    }
}
