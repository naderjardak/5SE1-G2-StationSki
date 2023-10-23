package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    Skieur skieur = new Skieur();

    Piste piste = new Piste();

    @Test
    public void testAssignSkieurToPiste_Success() {
        skieur.setIdSkieur(1L);
        piste.setIdPiste(1L);

        Mockito.when(skieurRepository.findByNumSkieur(1L)).thenReturn(skieur);
        Mockito.when(pisteRepository.findByNumPiste(1L)).thenReturn(piste);

        Skieur resultSki = skieurService.assignSkieurToPiste(1L, 1L);
        assertNotNull(resultSki.getIdSkieur());
    }

    @Test
    public void testAddSkieurAndAssignToCourse_Success() {
        skieur.setIdSkieur(1L);
        piste.setIdPiste(1L);

        SkieurModel skieurModel = new SkieurModel();
        Cours cours = new Cours();

        Mockito.when(coursRepository.findByNumCours(1L)).thenReturn(cours);
        Mockito.when(skieurRepository.save(any(Skieur.class))).thenReturn(skieur);

        Skieur result = skieurService.addSkieurAndAssignToCourse(skieurModel, 1L);
        assertNotNull(result.getInscriptions());
    }


    @Test
    public void testRetrieveSkieursByTypeAbonnement() {
        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL;
        Skieur skieur1 = new Skieur();
        Skieur skieur2 = new Skieur();

        Mockito.when(skieurRepository.findByAbonnementTypeAbon(typeAbonnement)).thenReturn(Arrays.asList(skieur1, skieur2));
        List<Skieur> result = skieurService.retrieveSkieursByTypeAbonnement(typeAbonnement);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(skieur1));
        Assertions.assertTrue(result.contains(skieur2));
    }

    @Test
    public void testNombreSkieursParCouleurPiste() {
        Map<Couleur, Integer> couleurCount = new EnumMap<>(Couleur.class);
        for (Couleur couleur : Couleur.values()) {
            couleurCount.put(couleur,0);
        }

        Mockito.when(skieurRepository.skieursByCouleurPiste(Mockito.any(Couleur.class))).thenReturn(new ArrayList<>());

        Map<Couleur, Integer> result = skieurService.nombreSkieursParCouleurPiste();
        Assertions.assertEquals(couleurCount, result);
    }
}

