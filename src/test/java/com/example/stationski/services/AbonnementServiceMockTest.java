package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import com.example.stationski.repositories.MoniteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class AbonnementServiceMockTest {

    @Mock
    private AbonnementRepository abonnementRepository;

    @Mock
    private MoniteurRepository moniteurRepository;

    @InjectMocks
    private MoniteurServiceImpl moniteurService;

    @InjectMocks
    private AbonnementService abonnementService;

    @Test
    void testGetAbonnementByType() {
        TypeAbonnement type = TypeAbonnement.ANNUEL;
        when(abonnementRepository.findByTypeAbon(type)).thenReturn(Set.of(new Abonnement()));

        Set<Abonnement> result = abonnementService.getAbonnementByType(type);

        assertEquals(1, result.size());
        verify(abonnementRepository, times(1)).findByTypeAbon(type);
    }

    @Test
    void testRetrieveAbonnementByDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10);

        when(abonnementRepository.getAbonnementsByDateDebutBetween(startDate, endDate))
                .thenReturn(Arrays.asList(new Abonnement(), new Abonnement()));

        List<Abonnement> result = abonnementService.retrieveAbonnementByDates(startDate, endDate);

        assertEquals(2, result.size());
        verify(abonnementRepository, times(1)).getAbonnementsByDateDebutBetween(startDate, endDate);
    }

    @Test
    void testGetAbonnementByIdWhenPresent() {
        int id = 1;
        Abonnement abonnement = new Abonnement();
        when(abonnementRepository.findById(id)).thenReturn(Optional.of(abonnement));

        Abonnement result = abonnementService.getAbonnementById(id);

        assertEquals(abonnement, result);
        verify(abonnementRepository, times(1)).findById(id);
    }

    @Test
    void testGetAbonnementByIdWhenNotPresent() {
        int id = 2;
        when(abonnementRepository.findById(id)).thenReturn(Optional.empty());

        Abonnement result = abonnementService.getAbonnementById(id);

        assertNull(result);
        verify(abonnementRepository, times(1)).findById(id);
    }

    @Test
    void testBestMoniteur() {
        Moniteur moniteur1 = new Moniteur();
        moniteur1.setIdMoniteur(1);
        Set<Cours> coursSet1 = new HashSet<>();
        coursSet1.add(new Cours());
        coursSet1.add(new Cours());
        moniteur1.setCoursSet(coursSet1);

        Moniteur moniteur2 = new Moniteur();
        moniteur2.setIdMoniteur(2);
        Set<Cours> coursSet2 = new HashSet<>();
        coursSet2.add(new Cours());
        moniteur2.setCoursSet(coursSet2);

        verify(moniteurRepository, never()).save(moniteur2);

    }
}