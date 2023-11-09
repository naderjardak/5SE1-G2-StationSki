package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
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
class AbonnementServiceTest {
    @Mock
    AbonnementRepository abonnementRepository;
    @InjectMocks
    AbonnementService abonnementService;

    @DisplayName("Get abonnement by id - succes scenario")
    @Test
    void test_when_abonnement_success(){
        //Mocking
        Abonnement abonnement = getMockAbonnement();
        when(abonnementRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(abonnement));
        //Actual
        Abonnement abonnementRespo = abonnementService.getAbonnementById(1);
        //Verification
        Mockito.verify(abonnementRepository,times(1)).findById(anyInt());
        //Assert
        Assertions.assertNotNull(abonnementRespo);
        Assertions.assertEquals(abonnementRespo.getIdAbonnement(),abonnement.getIdAbonnement());
    }
    private Abonnement getMockAbonnement(){
        return Abonnement.builder()
                .idAbonnement(1).numAbon(10L).typeAbon(TypeAbonnement.MENSUEL).build();
    }
    @DisplayName("get Abonnement By Type - succes scenario")
    @Test
    void test_get_abon_by_type() {
        // Mocking
        Abonnement abonnement = getMockAbonnement();
        Set<Abonnement> abonnementsSet = new HashSet<>();
        abonnementsSet.add(abonnement);

        when(abonnementRepository.findByTypeAbon(TypeAbonnement.ANNUEL))
                .thenReturn(abonnementsSet);

        Set<Abonnement> abonnementRespo = abonnementService.getAbonnementByType(TypeAbonnement.ANNUEL);

        Mockito.verify(abonnementRepository, times(1)).findByTypeAbon(TypeAbonnement.ANNUEL);
        Assertions.assertNotNull(abonnementRespo);
        Assertions.assertEquals(1, abonnementRespo.size());

    }

}