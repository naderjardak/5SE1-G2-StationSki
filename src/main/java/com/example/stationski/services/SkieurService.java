package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Slf4j
public class SkieurService implements ISkieurService{

    @Autowired
    SkieurRepository skieurRepository;
    @Autowired
    PisteRepository pisteRepository;
    @Autowired
    CoursRepository coursRepository;


    @Transactional
    public Skieur assignSkieurToPiste(Long numSkieur, Long numPiste) {
        log.info("debut methode assignSkieurToPiste");
        Skieur skieur = skieurRepository.findByNumSkieur(numSkieur);
        Piste piste = pisteRepository.findByNumPiste(numPiste);
        log.info("skieur " + skieur.getNumSkieur());
        log.info("piste " + piste.getNomPiste());
        skieur.setPistes(new HashSet<>());
        skieur.getPistes().add(piste);
        log.info("fin methode assignSkieurToPiste");
        return skieur;
    }

    @Transactional
    public Skieur addSkieurAndAssignToCourse(SkieurModel skieurModel, Long numCourse) {
        log.info("debut methode addSkieurAndAssignToCourse");
        Skieur skieur = new Skieur();
        skieur.setNumSkieur(skieurModel.getNumSkieur());
        skieur.setNomS(skieurModel.getNomS());
        skieur.setPrenomS(skieurModel.getPrenomS());
        skieur.setDateNaissance(skieurModel.getDateNaissance());
        skieur.setVille(skieurModel.getVille());
        skieur.setInscriptions(new HashSet<>());

        Skieur.builder().nomS("sahli").numSkieur(123L).build();
        Cours cours = coursRepository.findByNumCours(numCourse);
        Skieur s = skieurRepository.save(skieur);
        Inscription inscription = new Inscription();
        inscription.setCours(cours);
        inscription.setSkieur(s);
        skieur.getInscriptions().add(inscription);
        log.info("fin methode addSkieurAndAssignToCourse");
        return skieur;
    }

    @Override
    public List<Skieur> retrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement) {
        return skieurRepository.findByAbonnementTypeAbon(typeAbonnement);
    }

    @Override
    public Map<Couleur,Integer> nombreSkieursParCouleurPiste() {
        log.info("debut methode nombreSkieursParCouleurPiste");
        Map<Couleur,Integer> nombreSkieursParCouleurPiste = new EnumMap<>(Couleur.class);
        Couleur[] couleurs = Couleur.values();
        for(Couleur c: couleurs) {
            nombreSkieursParCouleurPiste.put(c,skieurRepository.skieursByCouleurPiste(c).size());

        }
        log.info("------------------------------------------------"+nombreSkieursParCouleurPiste.size());
        log.info("------------------------------------------------"+nombreSkieursParCouleurPiste);
        return nombreSkieursParCouleurPiste;
    }
}
