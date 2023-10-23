package com.example.stationski.services;
import com.example.stationski.entities.*;
import com.example.stationski.repositories.AbonnementRepository;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.time.LocalDate;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j

 class InscriptionServiceTest {


    @Autowired
    InscriptionRepository inscriptionRepository;

    @Autowired
    InscriptionService inscriptionService;
    @Autowired
    SkieurRepository skieurRepository;
    @Autowired
    CoursRepository coursRepository;


    @Autowired
    AbonnementRepository abonnementRepository;

   Skieur skieur = Skieur.builder()
           .numSkieur(25365478L)
           .nomS("kabbous")
           .prenomS("tessnime")
           .dateNaissance(LocalDate.of(1999, 8, 25))
           .ville("tunis")
           .build();
   Cours cours = Cours.builder()
           .numCours(23L)
           .typeCours(TypeCours.COLLECTIF_ADULTE)
           .support(Support.SNOWBOARD)
           .niveau(2)
           .creneau(4)
           .build();
   Abonnement abonnement=Abonnement.builder()
           .typeAbon(TypeAbonnement.ANNUEL)
           .prixAbon(3.5f)
           .numAbon(3L)
           .build();
   Inscription inscription=Inscription.builder()
           .numInscription(1L)
           .numSemaine(2)
           .build();

    @BeforeEach
    void setUp() {
        skieur = skieurRepository.save(skieur);
        cours = coursRepository.save(cours);
        skieur.setAbonnement(abonnement);
        skieur=skieurRepository.save(skieur);
        inscription.setSkieur(skieur);
        inscription.setCours(cours);

    }

    @AfterEach
    void tearDown(){
        skieurRepository.deleteAll();
        abonnementRepository.deleteAll();
        inscriptionRepository.deleteAll();
        coursRepository.deleteAll();
    }



    @Order(0)
    @Test
     void testGetSubscriptionByType() {
        inscription=inscriptionRepository.save(inscription);
        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL;

       Set<Inscription> inscriptionss = inscriptionRepository.findByTypeAbonnement(typeAbonnement);

        assertNotNull(inscriptionss);
       assertEquals(1, inscriptionss.size());
       log.info("get inscription==> " + inscriptionss.size());
    }

    @Order(1)
    @Test
     void testAssignInscriptionToCours() {

        Inscription inscription = new Inscription();
        inscription.setNumInscription(1L);
        inscriptionRepository.save(inscription);


       Cours cours = new Cours();
       cours.setNumCours(2L);
        coursRepository.save(cours);


        Inscription existingInscription = inscriptionRepository.findByNumInscription(1L);


        Cours existingCours = coursRepository.findByNumCours(2L);

        log.info("Existing Inscription: " + existingInscription);
        log.info("Existing Cours: " + existingCours);


        assertNotNull(existingInscription, "L'inscription n'existe pas.");
        assertNotNull(existingCours, "Le cours n'existe pas.");


        assertNull(existingInscription.getCours(), "L'inscription a déjà un cours attribué.");


        existingInscription.setCours(existingCours);
        inscriptionRepository.save(existingInscription);


        Inscription updatedInscription = inscriptionRepository.findByNumInscription(1L);
        assertNotNull(updatedInscription.getCours(), "L'attribution du cours a échoué.");
        assertEquals(cours.getNumCours(), updatedInscription.getCours().getNumCours(), "Numéro de cours incorrect.");

        log.info("Updated Inscription with Cours: " + updatedInscription);
    }





}
