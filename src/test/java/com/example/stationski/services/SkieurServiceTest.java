package com.example.stationski.services;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Piste;
import com.example.stationski.entities.Skieur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Slf4j
class SkieurServiceTest {

    @InjectMocks
    private SkieurService skieurService;

    @Mock
    SkieurRepository skieurRepository;

    @Mock
    PisteRepository pisteRepository;

    @Mock
    CoursRepository coursRepository;



    @Test
    @Order(0)
    void assignSkieurToPiste() {
        Skieur skieur = new Skieur();
        Piste piste = new Piste();

        skieur.setIdSkieur(1L);
        skieur.setNumSkieur(23L);
        skieur.setVille("tunis");
        skieur.setNomS("Nader");
        skieur.setPrenomS("jardak");
        skieur.setDateNaissance(LocalDate.of(1999, 2, 23));

        piste.setIdPiste(1L);
        piste.setNomPiste("piste24");
        piste.setNumPiste(12L);
        piste.setCouleur(Couleur.BLEU);
        piste.setLongeur(30);
        piste.setPente(40);

        piste=pisteRepository.save(piste);
        log.info(String.valueOf(piste.getNomPiste()));
        skieur=skieurRepository.save(skieur);
        log.info(String.valueOf(skieur.getNumSkieur()));
        Skieur resultat =skieurService.assignSkieurToPiste(skieur.getNumSkieur(),piste.getNumPiste());
        assertNotNull(resultat);
        assertTrue(resultat.getPistes().contains(piste));
    }

    @Test
    void addSkieurAndAssignToCourse() {

    }

    @Test
    void retrieveSkieursByTypeAbonnement() {

    }

    @Test
    void nombreSkieursParCouleurPiste() {

    }
}