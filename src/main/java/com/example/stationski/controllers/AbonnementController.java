package com.example.stationski.controllers;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.IAbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/abonnement")
public class AbonnementController {
    IAbonnementService abonnementService;
    @GetMapping("/retrieve-all-abonnementBytype/{typeAbonnement}")
    public Set<Abonnement> getAbonnementsBytype(@PathVariable("typeAbonnement") TypeAbonnement typeAbonnement) {
        return abonnementService.getAbonnementByType(typeAbonnement);
    }

    @GetMapping("/getAbonnementsByDates/{date1}/{date2}")
    public List<Abonnement> getAbonnementsByDates(@PathVariable("date1") @DateTimeFormat(pattern= "yyyy-MM-dd")  LocalDate startDate,
                                                     @PathVariable("date2") @DateTimeFormat(pattern= "yyyy-MM-dd")  LocalDate endDate){
        return abonnementService.retrieveAbonnementByDates(startDate, endDate);
    }
}
