package com.example.stationski.services;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Piste;
import com.example.stationski.entities.Skieur;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class SkieurServiceTest {

    @Autowired
    ISkieurService skieurService;

    @Autowired
    SkieurRepository skieurRepository;

    @Autowired
    PisteRepository pisteRepository;

    Skieur skieur = Skieur.builder().idSkieur(1L).numSkieur(23L).nomS("nader").prenomS("Jardak").dateNaissance(LocalDate.ofEpochDay(23/2/1999)).ville("tunis").build();
    Piste piste = Piste.builder().idPiste(1L).nomPiste("piste4").numPiste(34L).couleur(Couleur.BLEU).longeur(12).pente(3).build();

    @BeforeEach
    public void setUp() {
        piste = pisteRepository.save(piste);
        skieur = skieurRepository.save(skieur);
    }

    @Test
    @Order(0)
    void assignSkieurToPiste() {
        Skieur skieur1 =skieurService.assignSkieurToPiste(skieur.getNumSkieur(),piste.getNumPiste());
        assertNotNull(skieur1.getIdSkieur());
        assertTrue(skieur1.getPistes().contains(piste));
    }


    @Test
    @Order(1)
    void addSkieurAndAssignToCourse() {

    }

    @Test
    @Order(2)
    void retrieveSkieursByTypeAbonnement() {

    }

    @Test
    @Order(3)
    void nombreSkieursParCouleurPiste() {

    }
}