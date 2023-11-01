package com.example.stationski.services;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.entities.model.SkieurModel;

import java.util.List;
import java.util.Map;

public interface ISkieurService {

    Skieur assignSkieurToPiste(Long numSkieur, Long numPiste);

    Skieur addSkieurAndAssignToCourse(SkieurModel skieur, Long numCourse);

    List<Skieur> retrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement);

    Map<Couleur,Integer> nombreSkieursParCouleurPiste();


}