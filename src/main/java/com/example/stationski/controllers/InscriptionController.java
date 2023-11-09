package com.example.stationski.controllers;

import com.example.stationski.entities.Inscription;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.services.IInscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
@RestController
@AllArgsConstructor

public class InscriptionController {
    IInscriptionService inscriptionService;
    @GetMapping("/getInscriptionByTypeAbonnement/{typeAbonnement}")
    public Set<Inscription> getSubscriptionByType(@PathVariable("typeAbonnement") TypeAbonnement typeAbonnement) {return inscriptionService.getSubscriptionByType(typeAbonnement);}
}