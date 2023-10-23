package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.entities.models.SkieurModels;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class SkieurService implements ISkieurService{

    SkieurRepository skieurRepository;
    PisteRepository pisteRepository;
    CoursRepository coursRepository;
    @Transactional
    public Skieur assignSkieurToPiste(Long numSkieur, Long numPiste) {
        log.info("debut methode assignSkieurToPiste");
        Skieur skieur = skieurRepository.findByNumSkieur(numSkieur);
        Piste piste =pisteRepository.findByNumPiste(numPiste);
        log.info("skieur "+skieur.getNumSkieur());
        log.info("piste "+piste.getNomPiste());
        skieur.getPistes().add(piste);
        log.info("fin methode assignSkieurToPiste");

        return skieur;
    }

    @Transactional
    public Skieur addSkieurAndAssignToCourse(SkieurModels skieurModel, Long numCourse) {
        log.info("debut methode addSkieurAndAssignToCourse");
        Skieur skieur=new Skieur();
        skieur.setNumSkieur(skieurModel.getNumSkieur());
        skieur.setNomS(skieurModel.getNomS());
        skieur.setPrenomS(skieurModel.getPrenomS());
        skieur.setDateNaissance(skieurModel.getDateNaissance());
        skieur.setVille(skieurModel.getVille());

        Skieur.builder().nomS("sahli").numSkieur(123L).build();
        // t1 = date systeme
        Cours cours = coursRepository.findByNumCours(numCourse);
        Skieur s = skieurRepository.save(skieur);
        Set<Inscription> inscriptions ;
        inscriptions= s.getInscriptions();
        inscriptions.stream().forEach(
                inscription ->  {
                    inscription.setCours(cours);
                    inscription.setSkieur(s);
                }

        );
        log.info("fin methode addSkieurAndAssignToCourse");
        return null;
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
        log.info("fin methode nombreSkieursParCouleurPiste");

        return nombreSkieursParCouleurPiste;
    }

    //test
}