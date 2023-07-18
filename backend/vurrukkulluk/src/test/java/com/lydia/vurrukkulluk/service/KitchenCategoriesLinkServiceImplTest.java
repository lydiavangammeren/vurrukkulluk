package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategoriesLink;
import com.lydia.vurrukkulluk.repository.KitchenCategoriesLinkRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenCategoriesLinkServiceImplTest {

    @Mock
    KitchenCategoriesLink link;
    @Mock
    KitchenCategoriesLinkRepository repository;

    KitchenCategoriesLinkServiceImpl kitchenCategoriesLinkService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        kitchenCategoriesLinkService = new KitchenCategoriesLinkServiceImpl(repository);
    }

    @Test
    void saveKCLink() {

        when(repository.save(link)).thenReturn(link);
        assertEquals(link,kitchenCategoriesLinkService.saveKCLink(link));
    }

    @Test
    void getKCLinkByRecipeId() {
        List<KitchenCategoriesLink> links = new ArrayList<>();
        links.add(link);
        links.add(link);
        links.add(link);
        when(repository.findByRecipeId(1)).thenReturn(links);
        assertEquals(links,kitchenCategoriesLinkService.getKCLinkByRecipeId(1));
        verify(repository).findByRecipeId(1);

    }

    @Test
    void deleteById() {
        kitchenCategoriesLinkService.deleteById(1);
        verify(repository).deleteById(1);
    }
}