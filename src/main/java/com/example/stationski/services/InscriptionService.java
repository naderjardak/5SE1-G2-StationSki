package com.example.stationski.services;

import com.example.stationski.configs.UtilityFonction;
import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.InscriptionRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class InscriptionService implements IInscriptionService{
    InscriptionRepository inscriptionRepository;
    SkieurRepository skieurRepository;
    CoursRepository coursRepository;


    @Override
    public Set<Inscription> getSubscriptionByType(TypeAbonnement typeAbonnement) {
        return inscriptionRepository.findByTypeAbonnement(typeAbonnement);
    }

    @Override
    public Inscription assignInscriptionToCours(Long numInscription, Long numCours) {
        Inscription inscription=inscriptionRepository.findByNumInscription(numInscription);
        Cours cours=coursRepository.findByNumCours(numCours);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);
        return inscription;
    }

    @Transactional
    public Inscription addInscriptionAndAssignToSkieurAndCourse(Inscription inscription, Long numSkieur, Long numCours) {
        Cours cours = coursRepository.findByNumCours(numCours);
        Skieur skieur = skieurRepository.findByNumSkieur(numSkieur);

        if (cours != null && skieur != null) {
            int ageSkieur = UtilityFonction.calculateAge(skieur.getDateNaissance());
            log.info("Âge du skieur : " + ageSkieur);


            if (cours.getInscriptions() == null) {
                cours.setInscriptions(new HashSet<>());
            }

            if ((cours.getTypeCours() == TypeCours.COLLECTIF_ADULTE && ageSkieur > 18)
                    || (cours.getTypeCours() == TypeCours.COLLECTIF_ENFANT && ageSkieur < 18)) {
                if (cours.getInscriptions().size() < 6) {
                    Inscription ins = new Inscription();
                    ins.setSkieur(skieur);
                    ins.setCours(cours);
                    inscriptionRepository.save(ins);
                    log.info("Le nombre d'inscriptions en cours est " + cours.getInscriptions().size());
                    return ins;
                }
            } else {
                log.info("Le nombre maximal d'inscriptions pour ce cours est atteint ou l'âge est incompatible avec le cours.");
            }
        } else {
            log.error("Le cours ou le skieur est introuvable.");
        }

        return null;
    }



    @Override
    public List<Integer> numWeeksCoursOfMoniteurBySupport(Long numInstructor, Support support) {
        return inscriptionRepository.numWeeksCoursOfMoniteurBySupport(numInstructor, support);

    }


}