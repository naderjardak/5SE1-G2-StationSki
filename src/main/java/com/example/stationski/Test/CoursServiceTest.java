package com.example.stationski.Test;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Support;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.services.CoursService;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CoursServiceTest {
    @Mock
    private CoursRepository coursRepository;

    private CoursService coursService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize annotated mocks
        coursService = new CoursService(coursRepository);
    }

    @Test
    public void testListeCoursSnowBoard() {
        when(coursRepository.findBySupport(Support.SNOWBOARD)).thenReturn(createMockCourses());
        coursService.listeCoursSnowBoard();
        verify(coursRepository).findBySupport(Support.SNOWBOARD);
    }

    private List<Cours> createMockCourses() {
        return Arrays.asList();
    }
}
