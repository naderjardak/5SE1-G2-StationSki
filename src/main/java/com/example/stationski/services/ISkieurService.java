package com.example.stationski.services;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.models.SkieurModels;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;

import java.util.List;
import java.util.Map;

public interface ISkieurService {

    Skieur assignSkieurToPiste(Long numSkieur, Long numPiste);

    Skieur addSkieurAndAssignToCourse(SkieurModels skieur, Long numCourse);

    List<Skieur> retrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement);

    Map<Couleur,Integer> nombreSkieursParCouleurPiste();


}