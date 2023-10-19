package com.example.stationski;
import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import com.example.stationski.services.ISkieurService;
import com.example.stationski.services.SkieurService;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class SkieurServiceMockTest {

    ISkieurService skieurService=new SkieurService();

    SkieurRepository skieurRepository= Mockito.mock(SkieurRepository.class);
    PisteRepository pisteRepository= Mockito.mock(PisteRepository.class);

    @Mock
    CoursRepository coursRepository;

    Skieur skieur= Skieur.builder().idSkieur(1).numSkieur(23L).nomS("njr").ville("tunis").dateNaissance(LocalDate.ofEpochDay(23/02/1999)).build();
    List<Skieur> skieurList=new ArrayList<Skieur>(){
        {
            add(Skieur.builder().idSkieur(2).numSkieur(23L).nomS("njr1").ville("tunis").dateNaissance(LocalDate.ofEpochDay(23/02/1999)).build());
            add(Skieur.builder().idSkieur(3).numSkieur(23L).nomS("njr2").ville("tunis").dateNaissance(LocalDate.ofEpochDay(23/02/1999)).build());
        }
    };


    Piste piste = Piste.builder().idPiste(1).nomPiste("new12").numPiste(33L).longeur(145).couleur(Couleur.BLEU).build();


    @Test
    public void testAssignSkieurToPiste_Success() {
        Mockito.when(skieurRepository.findByNumSkieur(Mockito.anyLong())).thenReturn(skieur);
        Mockito.when(pisteRepository.findByNumPiste(Mockito.anyLong())).thenReturn(piste);

        Assertions.assertNotNull(skieurService.assignSkieurToPiste(1L, 2L));
    }

    @Test
    public void testAddSkieurAndAssignToCourse_Success() {
        SkieurModel skieurModel = new SkieurModel();
        Long numCourse = 1L;
        Cours cours = new Cours();
        Mockito.when(coursRepository.findByNumCours(eq(1L))).thenReturn(cours);
        Mockito.when(skieurRepository.save(any(Skieur.class))).thenReturn(skieur);

        Skieur result = skieurService.addSkieurAndAssignToCourse(skieurModel, numCourse);
        Assertions.assertNotNull(result.getInscriptions());

        result.getInscriptions().forEach(inscription -> {
            Assertions.assertEquals(cours, inscription.getCours());
            Assertions.assertEquals(skieur, inscription.getSkieur());
        });
    }
}
