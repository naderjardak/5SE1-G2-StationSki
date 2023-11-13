package com.example.stationski.services;

import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MoniteurServiceImpl implements IMoniteurService{

    MoniteurRepository moniteurRepository;
    CoursRepository coursRepository;
    @Override
    public List<Moniteur> retrieveAllMoniteurs() {
        return moniteurRepository.findAll();
    }

    @Override
    public Moniteur addMoniteur(Moniteur m) {
        return moniteurRepository.save(m);
    }

    @Override
    public Moniteur updateMoniteur(Moniteur m) {
        return moniteurRepository.save(m);
    }

    @Override
    public Moniteur retrieveMoniteur(Integer idMoniteur) {
        Optional<Moniteur> moniteurOptional = moniteurRepository.findById(idMoniteur);
        return moniteurOptional.orElseGet(Moniteur::new);
    }

    @Override
    public void deleteMoniteur(Integer idMoniteur) {
        moniteurRepository.deleteById(idMoniteur);
    }

    @Transactional
    public Moniteur addMoniteurAndAssignToCourse(Moniteur moniteur) {
        return moniteurRepository.save(moniteur);
    }

}