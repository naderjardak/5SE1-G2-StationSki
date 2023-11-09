package com.example.stationski.services;

import com.example.stationski.StationSkiApplication;
import com.example.stationski.entities.*;
import com.example.stationski.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@ContextConfiguration(classes = {StationSkiApplication.class}) // Replace YourApplicationClass with the actual class containing your Spring Boot application configuration
class InscriptionServiceTest {

    @Autowired
    InscriptionService inscriptionService;

    @Autowired
    InscriptionRepository inscriptionRepository;

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


    @Test
    @Order(0)
    void getSubscriptionByType() {
        Inscription inscription = new Inscription();
        inscription.setSkieur(skieur);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);

        Set<Inscription> subscriptions = inscriptionService.getSubscriptionByType(TypeAbonnement.ANNUEL);
        assertFalse(subscriptions.isEmpty());
    }

    @Test
    @Order(1)
    void assignInscriptionToCours() {
        Inscription inscription = new Inscription();
        inscription.setSkieur(skieur);
        inscriptionRepository.save(inscription);

        Inscription assignedInscription = inscriptionService.assignInscriptionToCours(inscription.getNumInscription(), cours.getNumCours());
        assertNotNull(assignedInscription);
        assertEquals(cours.getNumCours(), assignedInscription.getCours().getNumCours());
    }

    @Test
    @Order(2)
    void addInscriptionAndAssignToSkieurAndCourse() {
        Inscription inscription = new Inscription();
        inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, skieur.getNumSkieur(), cours.getNumCours());

        Inscription savedInscription = inscriptionRepository.findByNumInscription(inscription.getNumInscription());
        assertNotNull(savedInscription);
        assertEquals(skieur.getNumSkieur(), savedInscription.getSkieur().getNumSkieur());
        assertEquals(cours.getNumCours(), savedInscription.getCours().getNumCours());
    }

    @Test
    @Order(3)
    void numWeeksCoursOfMoniteurBySupport() {
        inscriptionService.addInscriptionAndAssignToSkieurAndCourse(new Inscription(), skieur.getNumSkieur(), cours.getNumCours());
        List<Integer> weeks = inscriptionService.numWeeksCoursOfMoniteurBySupport(skieur.getNumSkieur(), Support.SKI);
        System.out.println("Weeks: " + weeks);
        assertNotNull(weeks);

    }
}
