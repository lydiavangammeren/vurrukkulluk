package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.repository.ArticleUnitRepository;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleUnitServiceImpl implements ArticleUnitService{

    @Autowired
    private ArticleUnitRepository articleUnitRepository;

    @Override
    public ArticleUnit save(ArticleUnit articleUnit) {
        articleUnitRepository.save(articleUnit);
        return articleUnit;
    }

    @Override
    public Unit getDefaultUnitFromArticleId(int articleId) {
        return getDefaultUnitArticleFromArticleId(articleId).getUnit();
    }

    @Override
    public ArticleUnit getDefaultUnitArticleFromArticleId(int articleId) {
        return articleUnitRepository.getDefaultUnitArticleFromArticleId(articleId);
    }

    @Override
    public List<ArticleUnit> getAll() {
        return articleUnitRepository.findAll();
    }
}
