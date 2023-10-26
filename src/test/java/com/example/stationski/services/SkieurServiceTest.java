package com.example.stationski.services;
import com.example.stationski.StationSkiApplication;
import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@ContextConfiguration(classes = {StationSkiApplication.class})
class SkieurServiceTest {

    @Autowired
    SkieurRepository skieurRepository;

    @Autowired
    PisteRepository pisteRepository;

    @Autowired
    CoursRepository coursRepository;

    @Autowired
    InscriptionRepository inscriptionRepository;

    @Autowired
    AbonnementRepository abonnementRepository;

    Skieur skieur = Skieur.builder()
            .numSkieur(23L)
            .nomS("nader")
            .prenomS("Jardak")
            .dateNaissance(LocalDate.of(1999, 2, 23))
            .ville("tunis")
            .build();

    Piste piste = Piste.builder()
            .nomPiste("piste4")
            .numPiste(34L)
            .couleur(Couleur.BLEU)
            .longeur(12)
            .pente(3)
            .build();

    Cours cours = Cours.builder()
            .numCours(23L)
            .typeCours(TypeCours.COLLECTIF_ADULTE)
            .niveau(2)
            .build();
    Abonnement abonnement=Abonnement.builder()
            .typeAbon(TypeAbonnement.ANNUEL)
            .prixAbon(3.3f)
            .numAbon(34L)
            .build();

    @BeforeEach
    public void setUp()
    {
        piste=pisteRepository.save(piste);
        skieur = skieurRepository.save(skieur);
        cours = coursRepository.save(cours);
        abonnement = abonnementRepository.save(abonnement);
        skieur.setAbonnement(abonnement);
        skieur=skieurRepository.save(skieur);

    }


    @Test
    @Order(0)
    void assignSkieurToPiste() {
        skieur = skieurRepository.findByNumSkieur(skieur.getNumSkieur());
        piste = pisteRepository.findByNumPiste(piste.getNumPiste());
        if (skieur.getPistes() == null) {
            skieur.setPistes(new HashSet<>());
        }
        skieur.getPistes().add(piste);
        assertNotNull(skieur,"ERROR");
        assertTrue(skieur.getPistes().contains(piste));

    }

    @Test
    @Order(1)
    void addSkieurAndAssignToCourse() {
        SkieurModel skieurModel = new SkieurModel();
        skieurModel.setNumSkieur(skieur.getNumSkieur());
        skieurModel.setNomS(skieur.getNomS());
        skieurModel.setPrenomS(skieur.getPrenomS());
        skieurModel.setVille(skieur.getVille());

        Skieur skieur = new Skieur();
        skieur.setNumSkieur(skieurModel.getNumSkieur());
        skieur.setNomS(skieurModel.getNomS());
        skieur.setPrenomS(skieurModel.getPrenomS());
        skieur.setDateNaissance(skieurModel.getDateNaissance());
        skieur.setVille(skieurModel.getVille());
        skieur.setInscriptions(new HashSet<>());

        cours = coursRepository.findByNumCours(cours.getNumCours());
        Skieur s = skieurRepository.save(skieur);
        Inscription inscription = new Inscription();
        inscription.setCours(cours);
        inscription.setSkieur(s);
        skieur.getInscriptions().add(inscription);
        assertNotNull(skieur.getNomS());
        log.info("id======================================="+abonnement.getIdAbonnement());

    }

    @Test
    @Order(2)
    void retrieveSkieursByTypeAbonnement() {
        List<Skieur> skieurList=skieurRepository.findByAbonnementTypeAbon(TypeAbonnement.ANNUEL);
        assertTrue(skieurList.size()>0);
    }

    @Test
    @Order(3)
    void nombreSkieursParCouleurPiste() {
        Map<Couleur,Integer> nombreSkieursParCouleurPiste = new EnumMap<>(Couleur.class);

        Couleur[] couleurs = Couleur.values();
        for(Couleur c: couleurs) {
            nombreSkieursParCouleurPiste.put(c,skieurRepository.skieursByCouleurPiste(c).size());
        }
        assertEquals(4, nombreSkieursParCouleurPiste.size());
    }
}
