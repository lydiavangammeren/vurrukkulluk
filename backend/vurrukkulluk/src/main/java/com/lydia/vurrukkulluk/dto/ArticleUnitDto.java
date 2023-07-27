package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Unit;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUnitDto {
    private int id;
    private ArticleDto article;

    private UnitDto unit;
    private double amount;
    private UnitDto defUnit;
}
