package com.example.stationski.services;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Piste;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import com.example.stationski.services.SkieurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SkieurServiceMockTest {

    @InjectMocks
    private SkieurService skieurService;

    @Mock
    SkieurRepository skieurRepository;

    @Mock
    PisteRepository pisteRepository;

    @Mock
    CoursRepository coursRepository;

    @Test
    public void testAssignSkieurToPiste_Success() {
        Skieur skieur = new Skieur();
        skieur.setIdSkieur(1L);
        Piste piste = new Piste();
        piste.setIdPiste(1L);

        when(skieurRepository.findByNumSkieur(1L)).thenReturn(skieur);
        when(pisteRepository.findByNumPiste(1L)).thenReturn(piste);

        Skieur resultSki = skieurService.assignSkieurToPiste(1L, 1L);
        assertNotNull(resultSki.getIdSkieur());
    }

    @Test
    public void testAddSkieurAndAssignToCourse_Success() {
        Skieur skieur = new Skieur();
        skieur.setIdSkieur(1L);
        Piste piste = new Piste();
        piste.setIdPiste(1L);

        SkieurModel skieurModel = new SkieurModel();
        Long numCourse = 1L;
        Cours cours = new Cours();
        when(coursRepository.findByNumCours(eq(1L))).thenReturn(cours);
        when(skieurRepository.save(any(Skieur.class))).thenReturn(skieur);

        Skieur result = skieurService.addSkieurAndAssignToCourse(skieurModel, numCourse);
        assertNotNull(result.getInscriptions());

        result.getInscriptions().forEach(inscription -> {
            assertEquals(cours, inscription.getCours());
            assertEquals(skieur, inscription.getSkieur());
        });
    }
}
