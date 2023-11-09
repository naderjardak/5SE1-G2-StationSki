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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class InscriptionServiceMockTest {

    @InjectMocks
    private InscriptionService inscriptionService;

    @Mock
    private InscriptionRepository inscriptionRepository;

    @Mock
    private SkieurRepository skieurRepository;

    @Mock
    private CoursRepository coursRepository;





    @Test
    void testGetSubscriptionByType() {
        TypeAbonnement type = TypeAbonnement.ANNUEL;
        InscriptionService inscriptionService = Mockito.mock(InscriptionService.class);
        Mockito.when(inscriptionService.getSubscriptionByType(type)).thenReturn(Collections.emptySet());

        Set<Inscription> inscriptions = inscriptionService.getSubscriptionByType(type);

        assertNotNull(inscriptions);
        assertTrue(inscriptions.isEmpty());

    }

    @Test
    void testAssignInscriptionToCours() {

        Inscription inscription = new Inscription();
        Mockito.when(inscriptionRepository.findByNumInscription(2L)).thenReturn(inscription);


        Cours cours = new Cours();
        cours.setNumCours(1L);
        Mockito.when(coursRepository.findByNumCours(1L)).thenReturn(cours);

        Inscription result = inscriptionService.assignInscriptionToCours(2L, 1L);

        assertNotNull(result.getCours());
        assertEquals(cours, result.getCours());
        assertEquals(1L, result.getCours().getNumCours());
    }

    @Test
    void testAddInscriptionAndAssignToSkieurAndCourse_EmptyDatabase() {


        when(coursRepository.findByNumCours(Mockito.anyLong())).thenReturn(null);
        when(skieurRepository.findByNumSkieur(Mockito.anyLong())).thenReturn(null);

        Inscription inscription = new Inscription();
        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, 1L, 1L);


        assertNull(result);
    }

    @Test
    void testAddInscriptionAndAssignToSkieurAndCourse_CourseNotFound() {
        Skieur skieur = new Skieur();
        Inscription inscription = new Inscription();

        when(coursRepository.findByNumCours(anyLong())).thenReturn(null);
        when(skieurRepository.findByNumSkieur(anyLong())).thenReturn(skieur);

        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, 1L, 1L);

        assertNull(result);
    }

    @Test
    void testAddInscriptionAndAssignToSkieurAndCourse_SkieurNotFound() {
        Cours cours = new Cours();
        Inscription inscription = new Inscription();

        when(coursRepository.findByNumCours(anyLong())).thenReturn(cours);
        when(skieurRepository.findByNumSkieur(anyLong())).thenReturn(null);

        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, 1L, 1L);

        assertNull(result);
    }

    @Test
    void testAddInscriptionAndAssignToSkieurAndCourse_IncompatibleAge() {
        Cours cours = new Cours();
        Skieur skieur = new Skieur();
        skieur.setDateNaissance(LocalDate.of(2000, 1, 1)); // Supposez que le skieur est né en 2000.
        Inscription inscription = new Inscription();

        when(coursRepository.findByNumCours(anyLong())).thenReturn(cours);
        when(skieurRepository.findByNumSkieur(anyLong())).thenReturn(skieur);

        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, 1L, 1L);

        assertNull(result);
    }

    @Test
    void testAddInscriptionAndAssignToSkieurAndCourse_MaxInscriptionsReached() {
        Cours cours = new Cours();
        Skieur skieur = new Skieur();
        Inscription inscription = new Inscription();


        Set<Inscription> existingInscriptions = IntStream.range(1, 7)
                .mapToObj(i -> new Inscription())
                .collect(Collectors.toSet());
        cours.setInscriptions(existingInscriptions);

        when(coursRepository.findByNumCours(anyLong())).thenReturn(cours);
        when(skieurRepository.findByNumSkieur(anyLong())).thenReturn(skieur);

        Inscription result = inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, 1L, 1L);

        assertNull(result);
        log.info("Le nombre d'inscriptions en cours est " + cours.getInscriptions().size());
    }


    @Test
    void testNumWeeksCoursOfMoniteurBySupport() {

        Long numInstructor = 1L;
        Support support = Support.SKI;


        List<Integer> numWeeks = List.of(1,2,3);


        when(inscriptionRepository.numWeeksCoursOfMoniteurBySupport(numInstructor, support)).thenReturn(numWeeks);


        List<Integer> result = inscriptionService.numWeeksCoursOfMoniteurBySupport(numInstructor, support);


        assertEquals(numWeeks, result);


        verify(inscriptionRepository).numWeeksCoursOfMoniteurBySupport(numInstructor, support);
    }




}