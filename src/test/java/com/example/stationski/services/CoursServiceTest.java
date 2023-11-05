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

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@ContextConfiguration(classes = {StationSkiApplication.class})
class CoursServiceTest {
    @Autowired
    ICoursService coursService;

    @Autowired
    CoursRepository coursRepository;





    Cours cours = Cours.builder()
            .numCours(23L)
            .typeCours(TypeCours.COLLECTIF_ADULTE)
            .niveau(2)
            .build();


    @BeforeEach
    public void setUp()
    {

        cours = coursRepository.save(cours);


    }
    @AfterEach
    void tearDown(){

        coursRepository.deleteAll();
    }


    @Test
    @Order(0)
    void addCours() {
        Cours cours1 = new Cours();
        cours1.setNumCours(cours.getNumCours());
        cours1.setTypeCours(cours.getTypeCours());
        cours1.setNiveau(cours.getNiveau());
        cours1.setPrix(cours.getPrix());
        cours1.setCreneau(cours.getCreneau());
        cours1.setSupport(cours.getSupport());
        Set<Inscription> inscriptions = new HashSet<>();
        Inscription inscription = new Inscription();
        inscription.setCours(cours);

        inscriptions.add(inscription);

        cours1.setInscriptions(inscriptions);

        Cours addedCours = coursService.addCours(cours1);

        assertEquals(1, addedCours.getInscriptions().size());

    }


    @Test
    @Order(1)
    void getCoursByType() {
        Cours cours1 = new Cours();
        cours1.setNumCours(cours.getNumCours());
        cours1.setTypeCours(cours.getTypeCours());
        cours1.setNiveau(cours.getNiveau());
        cours1.setPrix(cours.getPrix());
        cours1.setCreneau(cours.getCreneau());
        cours1.setSupport(cours.getSupport());
        cours=coursRepository.save(cours);

        Set<Cours> cours = coursRepository.findByTypeCours(TypeCours.PARTICULIER);

        assertNotNull(cours1);
        assertEquals(0, cours.size());
        log.info("get cours==> " + cours.size());
    }





}