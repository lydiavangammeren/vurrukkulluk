package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Preparation;
import java.util.List;

public interface PreparationService {
    public List<Preparation> getAllPreparations();

    public List<Preparation> getAllPreparationsRecipe(int id);
    public void savePreparation(Preparation preparation);

}
