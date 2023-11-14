package com.example.stationski;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.services.IMoniteurService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
   private MoniteurRepository moniteurRepository;

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
        Moniteur addedMoniteur = moniteurRepository.save(moniteur);
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

        moniteurRepository.save(moniteur1);
        moniteurRepository.save(moniteur2);

        List<Moniteur> allMoniteurs = moniteurRepository.findAll();
        assertNotNull(allMoniteurs);
        assertEquals(2, allMoniteurs.size());
    }

    @Test
    @Order(2)
    void updateMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Charlie");
        moniteur.setPrenomM("Chaplin");

        Moniteur addedMoniteur = moniteurRepository.save(moniteur);
        assertNotNull(addedMoniteur);

        addedMoniteur.setNomM("UpdatedName");
        Moniteur updatedMoniteur = moniteurRepository.save(addedMoniteur);

        assertNotNull(updatedMoniteur);
        assertEquals("UpdatedName", updatedMoniteur.getNomM());
    }

    @Test
    @Order(3)
    void retrieveMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("David");
        moniteur.setPrenomM("Duchovny");

        Moniteur addedMoniteur = moniteurRepository.save(moniteur);
        assertNotNull(addedMoniteur);

        Optional<Moniteur> optionalMoniteur = moniteurRepository.findById(addedMoniteur.getIdMoniteur());
        if(optionalMoniteur.isPresent()){
            Moniteur retrievedMoniteur=optionalMoniteur.get();
            assertNotNull(retrievedMoniteur);
            assertEquals("David", retrievedMoniteur.getNomM());

        }
    }

    @Test
    @Order(4)
    void deleteMoniteur() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Eva");
        moniteur.setPrenomM("Eve");

        Moniteur addedMoniteur = moniteurRepository.save(moniteur);
        assertNotNull(addedMoniteur);

        moniteurRepository.deleteById(addedMoniteur.getIdMoniteur());

        List<Moniteur> allMoniteurs = moniteurRepository.findAll();
        assertNotNull(allMoniteurs);
        assertEquals(0, allMoniteurs.size());
    }

    @Test
    @Order(5)
    void addMoniteurAndAssignToCourse() {
        Moniteur moniteur = new Moniteur();
        moniteur.setNomM("Frank");
        moniteur.setPrenomM("Frankenstein");
        Cours cours=new Cours();
        Cours cours1=new Cours();
        Set<Cours> coursSet = new HashSet<>();

        // Add Cours objects to the Set
        coursSet.add(cours);
        coursSet.add(cours1);
        moniteur.setCoursSet(coursSet);
        assertNotNull(moniteur);
        assertNotNull(moniteur.getCoursSet());
        assertEquals(2, moniteur.getCoursSet().size());
    }

    // Mockito Tests

    @Mock
    private IMoniteurService moniteurServiceMock;

    @Test
    @Order(6)
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
