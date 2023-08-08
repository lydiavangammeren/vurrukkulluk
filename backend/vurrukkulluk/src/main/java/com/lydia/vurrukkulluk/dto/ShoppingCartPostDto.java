package com.lydia.vurrukkulluk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartPostDto {
    private HashMap<Integer,Integer> recipeIdsPersonMap;
}
