package com.example.stationski.services;

import com.example.stationski.entities.Cours;

import com.example.stationski.entities.TypeCours;
import com.example.stationski.repositories.CoursRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CoursService implements   ICoursService{
    CoursRepository coursRepository;
    @Override

    public Cours addCours(Cours cours) {

        return coursRepository.save(cours);
    }
    @Override
    public Cours getCoursById(int id){
        return coursRepository.findById(id).orElse(null);
    }
    @Override
    public Set<Cours> getCoursByType(TypeCours type) {
        return coursRepository.findByTypeCours(type);

    }


}


