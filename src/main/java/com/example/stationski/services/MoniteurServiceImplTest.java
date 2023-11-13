package com.example.stationski.services;

import com.example.stationski.StationSkiApplication;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@ContextConfiguration(classes = {StationSkiApplication.class})
class MoniteurServiceImplTest {

    @Autowired
    IMoniteurService moniteurService;

    @Autowired
    MoniteurRepository moniteurRepository;

    @Autowired
    CoursRepository coursRepository;

    @BeforeEach
    public void setUp() {
        // Your setup logic, if any
    }

    @AfterEach
    void tearDown() {
        moniteurRepository.deleteAll();
    }

    // Traditional Tests

    @Test
    @Order(0)
    void addMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("John");
        moniteur.setPrenomM("Doe");
        Moniteur addedMoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(addedMoniteur);
        assertEquals("John", addedMoniteur.getNomM());
    }

    @Test
    @Order(1)
    void retrieveAllMoniteurs() {
        Moniteur moniteur1 = new Moniteur();
        moniteur1.setNomM("Alice");
        moniteur1.setPrenomM("Wonderland");
        Moniteur moniteur2 = new Moniteur();
        moniteur2.setNomM("Bob");
        moniteur2.setPrenomM("Builder");

        moniteurService.addMoniteur(moniteur1);
        moniteurService.addMoniteur(moniteur2);

        List<Moniteur> allMoniteurs = moniteurService.retrieveAllMoniteurs();
        assertNotNull(allMoniteurs);
        assertEquals(2, allMoniteurs.size());
    }

    @Test
    @Order(2)
    void updateMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Charlie");
        moniteur.setPrenomM("Chaplin");

        Moniteur addedMoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(addedMoniteur);

        addedMoniteur.setNomM("UpdatedName");
        Moniteur updatedMoniteur = moniteurService.updateMoniteur(addedMoniteur);

        assertNotNull(updatedMoniteur);
        assertEquals("UpdatedName", updatedMoniteur.getNomM());
    }

    @Test
    @Order(3)
    void retrieveMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("David");
        moniteur.setPrenomM("Duchovny");

        Moniteur addedMoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(addedMoniteur);

        Moniteur retrievedMoniteur = moniteurService.retrieveMoniteur(addedMoniteur.getIdMoniteur());

        assertNotNull(retrievedMoniteur);
        assertEquals("David", retrievedMoniteur.getNomM());
    }

    @Test
    @Order(4)
    void deleteMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Eva");
        moniteur.setPrenomM("Eve");

        Moniteur addedMoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(addedMoniteur);

        moniteurService.deleteMoniteur(addedMoniteur.getIdMoniteur());

        List<Moniteur> allMoniteurs = moniteurService.retrieveAllMoniteurs();
        assertNotNull(allMoniteurs);
        assertEquals(0, allMoniteurs.size());
    }

    @Test
    @Order(5)
    void addMoniteurAndAssignToCourse() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Frank");
        moniteur.setPrenomM("Frankenstein");

        Moniteur addedMoniteur = moniteurService.addMoniteurAndAssignToCourse(moniteur);

        assertNotNull(addedMoniteur);
        assertNotNull(addedMoniteur.getCoursSet());
        assertEquals(1, addedMoniteur.getCoursSet().size());
    }

    @Test
    @Order(6)
    void bestMoniteur() {
        Moniteur moniteur1 = new Moniteur();
        moniteur1.setNomM("Gina");
        moniteur1.setPrenomM("Genius");

        Moniteur moniteur2 = new Moniteur();
        moniteur2.setNomM("Harry");
        moniteur2.setPrenomM("Houdini");

        Moniteur addedMoniteur1 = moniteurService.addMoniteurAndAssignToCourse(moniteur1);
        assertNotNull(addedMoniteur1);

        Moniteur addedMoniteur2 = moniteurService.addMoniteurAndAssignToCourse(moniteur2);
        assertNotNull(addedMoniteur2);

        Moniteur bestMoniteur = moniteurService.bestMoniteur();

        assertNotNull(bestMoniteur);
        assertEquals("Gina", bestMoniteur.getNomM());
    }

    // Mockito Tests

    @Mock
    private IMoniteurService moniteurServiceMock;

    @Test
    @Order(7)
    void bestMoniteurMockito() {
        Moniteur moniteur1 = new Moniteur();
        moniteur1.setNomM("Gina");
        moniteur1.setPrenomM("Genius");

        Moniteur moniteur2 = new Moniteur();
        moniteur2.setNomM("Harry");
        moniteur2.setPrenomM("Houdini");

        when(moniteurServiceMock.bestMoniteur()).thenReturn(moniteur1);

        Moniteur bestMoniteur = moniteurServiceMock.bestMoniteur();

        assertNotNull(bestMoniteur);
        assertEquals("Gina", bestMoniteur.getNomM());

        // Verify that the method was called
        verify(moniteurServiceMock, times(1)).bestMoniteur();
    }
}
