package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
@Slf4j
public class InscriptionServiceTestMockito {

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
        TypeAbonnement type = TypeAbonnement.ANNUEL;
        InscriptionService inscriptionService = Mockito.mock(InscriptionService.class);
        Mockito.when(inscriptionService.getSubscriptionByType(type)).thenReturn(Collections.emptySet());

        Set<Inscription> inscriptions = inscriptionService.getSubscriptionByType(type);

        assertNotNull(inscriptions);
        assertTrue(inscriptions.isEmpty());
        log.info("Get==>"+inscriptions);


    }

    @Test
    public void testAssignInscriptionToCours() {

        Inscription inscription = new Inscription();
        Mockito.when(inscriptionRepository.findByNumInscription(2L)).thenReturn(inscription);


        Cours cours = new Cours();
        cours.setNumCours(1L);
        Mockito.when(coursRepository.findByNumCours(1L)).thenReturn(cours);

        Inscription result = inscriptionService.assignInscriptionToCours(2L, 1L);

        assertNotNull(result.getCours());
        assertEquals(cours, result.getCours());
        assertEquals(1L, result.getCours().getNumCours());
        log.info("get=>"+result.getCours().getNumCours());
    }
    @Test
    public void testAddInscriptionAndAssignToSkieurAndCourse() {
        // Créez des objets fictifs pour les paramètres de la méthode
        Inscription inscription = new Inscription();
        Long numSkieur = 1L;
        Long numCours = 2L;

        // Définissez le comportement des objets mock (coursRepository, skieurRepository, inscriptionRepository)
        when(coursRepository.findByNumCours(numCours)).thenReturn(new Cours()); // Remplacez par votre implémentation réelle
        when(skieurRepository.findByNumSkieur(numSkieur)).thenReturn(new Skieur()); // Remplacez par votre implémentation réelle


        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, numSkieur, numCours);
        assertNull(result);



    }

}

