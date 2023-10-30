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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CoursServiceTest {
    @Mock
    CoursRepository coursRepository;
    @InjectMocks

    CoursService coursService;

    
    @DisplayName("Get cours by id - succes scenario")
    @Test
    void test_when_cours_success(){
        //Mocking
        Cours cours = getMockCours();
        when(coursRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(cours));
        //Actual
        Cours coursRespo = coursService.getCoursById(1);
        //Verification
        Mockito.verify(coursRepository,times(1)).findById(anyInt());
        //Assert
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
        // Mocking
        Cours cours = getMockCours();
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(cours);

        when(coursRepository.findByTypeCours(TypeCours.PARTICULIER))
                .thenReturn(coursSet);

        // Actual
        Set<Cours> coursRespo = coursService.getCoursByType(TypeCours.PARTICULIER);

        // Verification
        Mockito.verify(coursRepository, times(1)).findByTypeCours(TypeCours.PARTICULIER);

        // Assert
        Assertions.assertNotNull(coursRespo);
        Assertions.assertEquals(1, coursRespo.size());

    }

}