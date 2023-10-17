import com.example.stationski.SkieurService;
import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkieurServiceTest {

    @InjectMocks
    private SkieurService skieurService;

    @Mock
    private SkieurRepository skieurRepository;

    @Mock
    private PisteRepository pisteRepository;

    @Mock
    private CoursRepository coursRepository;


    @Test
    public void testAssignSkieurToPiste_Success() {
        Skieur skieur = new Skieur();
        Piste piste = new Piste();
        when(skieurRepository.findByNumSkieur(anyLong())).thenReturn(skieur);
        when(pisteRepository.findByNumPiste(anyLong())).thenReturn(piste);
        Skieur result = skieurService.assignSkieurToPiste(123L, 456L);
        verify(skieurRepository).findByNumSkieur(123L);
        verify(pisteRepository).findByNumPiste(456L);
        assertNotNull(result);
    }

    @Test
    public void testAddSkieurAndAssignToCourse_Success() {
        SkieurModel skieurModel = new SkieurModel();
        Skieur skieur = Mockito.mock(Skieur.class);
        Long numCourse = 1L;
        Cours cours = new Cours();
        when(coursRepository.findByNumCours(anyLong())).thenReturn(cours);
        when(skieurRepository.save(any(Skieur.class))).thenReturn(skieur);
        Skieur result = skieurService.addSkieurAndAssignToCourse(skieurModel, numCourse);
        assertNotNull(result.getInscriptions());
        result.getInscriptions().forEach(inscription -> {
            assertEquals(cours, inscription.getCours());
            assertEquals(skieur, inscription.getSkieur());
        });
        verify(coursRepository).findByNumCours(numCourse);
        verify(skieurRepository).save(any(Skieur.class));
    }


}