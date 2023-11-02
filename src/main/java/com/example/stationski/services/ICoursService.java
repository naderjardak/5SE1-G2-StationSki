package com.example.stationski.services;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.TypeCours;

import java.util.Set;

public interface ICoursService {
    Cours addCours(Cours cours);

    Cours getCoursById(int id);

    Set<Cours> getCoursByType(TypeCours type);


}
