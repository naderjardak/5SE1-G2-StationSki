package com.example.stationski.test;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;
import com.example.stationski.services.InscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class InscriptionServiceTest {

    @InjectMocks
    private InscriptionService inscriptionService;

    @Mock
    private InscriptionRepository inscriptionRepository;

    @Mock
    private SkieurRepository skieurRepository;

    @Mock
    private CoursRepository coursRepository;



    @Test
    public void testGetSubscriptionByType() {
        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL; // Replace with a valid type
        // Mock the behavior of inscriptionRepository to return a set of inscriptions
        when(inscriptionRepository.findByTypeAbonnement(typeAbonnement)).thenReturn(new HashSet<>());

        Set<Inscription> subscriptions = inscriptionService.getSubscriptionByType(typeAbonnement);
        assertNotNull(subscriptions);
        // You can add more assertions here as needed
    }

    @Test
    public void testAssignInscriptionToCours() {
        Long numInscription = 1L; // Replace with a valid ID
        Long numCours = 2L; // Replace with a valid ID
        Inscription inscription = new Inscription(); // Create a valid Inscription object

        // Mock the behavior of inscriptionRepository and coursRepository
        when(inscriptionRepository.findByNumInscription(numInscription)).thenReturn(inscription);
        when(coursRepository.findByNumCours(numCours)).thenReturn(new Cours()); // Replace with a valid Cours object

        Inscription result = inscriptionService.assignInscriptionToCours(numInscription, numCours);
        assertNotNull(result);
        // You can add more assertions here as needed
    }



}

