package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.repository.PreparationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PreparationServiceImpl implements PreparationService {
    @Autowired
    private PreparationRepository preparationRepository;
    @Override
    public List<Preparation> getAllPreparations() {
        return preparationRepository.findAll();
    }

    @Override
    public List<Preparation> getAllPreparationsRecipe(int id) {
        return preparationRepository.findByRecipeId(id);
    }

    @Override
    public Preparation savePreparation(Preparation preparation) {
        return preparationRepository.save(preparation);
    }

    @Override
    public void deleteById(int id) {
        preparationRepository.deleteById(id);
    }

}
