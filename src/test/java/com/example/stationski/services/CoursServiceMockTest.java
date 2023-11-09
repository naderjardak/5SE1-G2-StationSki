package com.example.stationski.services;


import com.example.stationski.entities.Cours;
import com.example.stationski.entities.TypeCours;
import com.example.stationski.repositories.CoursRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CoursServiceMockTest {
    @Mock
    CoursRepository coursRepository;
    @InjectMocks

    CoursService coursService;

    @Test
    void addCours() {
        Cours cours =new Cours();
        Assertions.assertNotNull(coursRepository.save(cours));
    }

    @DisplayName("Get cours by id - succes scenario")
    @Test
    void test_when_cours_success(){
        Cours cours = getMockCours();
        when(coursRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(cours));
        Cours coursRespo = coursService.getCoursById(1);
        Mockito.verify(coursRepository,times(1)).findById(anyInt());
        Assertions.assertNotNull(coursRespo);
        Assertions.assertEquals(coursRespo.getIdCours(),cours.getIdCours());
    }
    private Cours getMockCours() {
        return Cours.builder()
                .idCours(1).numCours(10L).typeCours(TypeCours.PARTICULIER).build();
    }
    @DisplayName("get Cours By Type - succes scenario")
    @Test
    void test_get_cours_by_type() {
        Cours cours = getMockCours();
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(cours);

        when(coursRepository.findByTypeCours(TypeCours.PARTICULIER))
                .thenReturn(coursSet);

        Set<Cours> coursRespo = coursService.getCoursByType(TypeCours.PARTICULIER);

        Mockito.verify(coursRepository, times(1)).findByTypeCours(TypeCours.PARTICULIER);

        Assertions.assertNotNull(coursRespo);
        Assertions.assertEquals(1, coursRespo.size());

    }

}