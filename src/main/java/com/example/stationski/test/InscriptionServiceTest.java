package com.example.stationski.test;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;
import com.example.stationski.services.InscriptionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;

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
        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL;
        Set<Inscription> expectedInscriptions = new HashSet<>();


        when(inscriptionRepository.findByTypeAbonnement(typeAbonnement)).thenReturn(expectedInscriptions);

        Set<Inscription> inscriptions = inscriptionService.getSubscriptionByType(typeAbonnement);
        assertNotNull(inscriptions);


    }
    @Test
    public void testAssignInscriptionToCours() {
        Long numInscription = 1L;
        Long numCours = 2L;
        Inscription inscription = new Inscription();

        when(inscriptionRepository.findByNumInscription(numInscription)).thenReturn(inscription);
        when(coursRepository.findByNumCours(numCours)).thenReturn(new Cours());


        Inscription result = inscriptionService.assignInscriptionToCours(numInscription, numCours);
        assertNotNull(result);


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




