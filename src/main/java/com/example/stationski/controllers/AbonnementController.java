package com.example.stationski.controllers;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.services.IAbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/abonnement")
public class AbonnementController {
    @Autowired
    IAbonnementService abonnementService;
    @GetMapping("/retrieve-all-abonnementBytype")
    public Set<Abonnement> getAbonnementsBytype(@RequestParam("typeAbonnement") TypeAbonnement typeAbonnement) {
        return abonnementService.getAbonnementByType(typeAbonnement);
    }

    @GetMapping("/getAbonnementsByDates")
    public List<Abonnement> getAbonnementsByDates(@RequestParam("date1") @DateTimeFormat(pattern= "yyyy-MM-dd")  LocalDate startDate,
                                                     @RequestParam("date2") @DateTimeFormat(pattern= "yyyy-MM-dd")  LocalDate endDate){
        return abonnementService.retrieveAbonnementByDates(startDate, endDate);
    }
}
