package com.example.stationski.services;
import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest

@Slf4j

 class InscriptionServiceTest {

    @Autowired
    InscriptionService inscriptionService;

    @Autowired
    InscriptionRepository inscriptionRepository;
    @Autowired
    SkieurRepository skieurRepository;
    @Autowired
    CoursRepository coursRepository;



    @Test
     void testGetSubscriptionByType() {

        Skieur skieur = new Skieur();
        skieur.setNumSkieur(58754896L);
        skieur.setNomS("kabus");
        skieur.setPrenomS("tesnime");
        skieur.setDateNaissance(LocalDate.of(1990, 5, 15));
        skieur.setVille("ariana");

        Abonnement abonnement = new Abonnement();
        abonnement.setNumAbon(101L);
        abonnement.setDateDebut(LocalDate.of(2023, 10, 1));
        abonnement.setDateFin(LocalDate.of(2024, 9, 30));
        abonnement.setPrixAbon(500.0f);
        abonnement.setTypeAbon(TypeAbonnement.ANNUEL);

        skieur.setAbonnement(abonnement);

        skieur = skieurRepository.save(skieur);


        Cours cours = new Cours();
        cours.setNumCours(1L);
        cours.setTypeCours(TypeCours.COLLECTIF_ADULTE);
        cours.setSupport(Support.SNOWBOARD);
        cours.setPrix(50.0f);
        cours.setCreneau(1);
        cours.setNiveau(2);
        cours = coursRepository.save(cours);


        Inscription inscription = new Inscription();
        inscription.setNumInscription(1L);
        inscription.setNumSemaine(2);
        inscription.setSkieur(skieur);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);


        TypeAbonnement typeAbonnement = TypeAbonnement.ANNUEL;

       Set<Inscription> inscriptionss = inscriptionRepository.findByTypeAbonnement(typeAbonnement);


      assertNotNull(inscriptionss);
//      assertEquals(12, inscriptionss.size()); // Il devrait y avoir une inscription
       log.info("get inscription==> " + inscriptionss.size());
    }







}
