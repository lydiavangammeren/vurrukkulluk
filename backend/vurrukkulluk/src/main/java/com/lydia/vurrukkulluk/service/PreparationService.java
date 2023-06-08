package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Preparation;
import java.util.List;

public interface PreparationService {
     List<Preparation> getAllPreparations();

     List<Preparation> getAllPreparationsRecipe(int id);
     void savePreparation(Preparation preparation);
     void deleteById(int id);
}
