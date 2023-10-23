package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AbonnementServiceTest {
    @Mock
    AbonnementRepository abonnementRepository;
    @InjectMocks
    AbonnementService abonnementService;

    @DisplayName("Get abonnement - succes scenario")
    @Test
    void test_when_order_success(){
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
                         .idAbonnement(1).numAbon(10L).typeAbon(TypeAbonnement.ANNUEL).build();
    }
}