package com.example.stationski.services;

import com.example.stationski.dto.AbonnementDto;
import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AbonnementService implements IAbonnementService{

    AbonnementRepository abonnementRepository;
    ModelMapper modelMapper;
    @Override
    public Set<Abonnement> getAbonnementByType(TypeAbonnement type) {
        return abonnementRepository.findByTypeAbon(type);
    }

    @Override
    public List<Abonnement> retrieveAbonnementByDates(LocalDate startDate, LocalDate endDate) {
        return abonnementRepository.getAbonnementsByDateDebutBetween(startDate, endDate);

    }

    @Override
    public Abonnement getAbonnementById(int id) {
        return abonnementRepository.findById(id).get();
    }

    @Override
    public Abonnement addAboonement(AbonnementDto abonnementDto) {
        Abonnement abonnement=modelMapper.map(abonnementDto,Abonnement.class);
        Abonnement saveAbo=abonnementRepository.save(abonnement);
        return saveAbo;
    }

    @Override
    public List<Abonnement> getAllAbonnement() {
        return abonnementRepository.findAll();
    }


}
