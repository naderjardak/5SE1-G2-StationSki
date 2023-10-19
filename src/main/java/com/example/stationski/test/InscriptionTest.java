package com.example.stationski.test;


import com.example.stationski.entities.Inscription;

import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.services.IInscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InscriptionTest {
    @Autowired
    IInscriptionService iInscriptionService;

    @Autowired
    InscriptionRepository inscriptionRepository;

    @Test
    public void testGetSubscriptionByType() {
        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL;
        Set<Inscription> expectedInscriptions = new HashSet<>();


        when(inscriptionRepository.findByTypeAbonnement(typeAbonnement)).thenReturn(expectedInscriptions);

        Set<Inscription> inscriptions = iInscriptionService.getSubscriptionByType(typeAbonnement);
        assertNotNull(inscriptions);


    }


}
