package com.lydia.vurrukkulluk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    HashMap<Integer,Integer>  ArticlesToBuy;
    HashMap<Integer,Double> ArticlesAmount;
}
