package com.example.stationski.services;

import com.example.stationski.entities.Inscription;
import com.example.stationski.entities.Support;
import com.example.stationski.entities.TypeAbonnement;

import java.util.List;

public interface IInscriptionService {
    public List<Inscription> getSubscriptionByType(TypeAbonnement typeAbonnement);

    Inscription assignInscriptionToCours(Long numInscription, Long numCours);

    Inscription addInscriptionAndAssignToSkieurAndCourse(Inscription inscription, Long numSkieur, Long numCours);

    List<Integer> numWeeksCoursOfMoniteurBySupport(Long numInstructor, Support support);

}