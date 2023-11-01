package com.example.stationski.controllers;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.services.ISkieurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("skieur")
public class SkieurController {
    @Autowired
    ISkieurService skieurService;
    @PutMapping("assignSkieurToPiste")
    public Skieur assignSkieurToPiste(@RequestParam("numSkieur")Long numSkieur,
                                      @RequestParam("numPiste")Long numPiste) {
        return skieurService.assignSkieurToPiste(numSkieur,numPiste);
    }

    @PostMapping("addSkieurAndAssignToCourse")
    public Skieur addSkieurAndAssignToCourse(@RequestBody SkieurModel skieurM,
                                             @RequestParam("numCours")Long numCours) {
        return skieurService.addSkieurAndAssignToCourse(skieurM,numCours);
    }


    @GetMapping("retrieveSkieursByTypeAbonnement")
    public List<Skieur> retrieveSkieursByTypeAbonnement(@RequestParam("typeAbonnement") TypeAbonnement typeAbonnement) {
        return skieurService.retrieveSkieursByTypeAbonnement(typeAbonnement);
    }

    @GetMapping("nombreSkieursParCouleurPiste")
    public Map<Couleur,Integer> nombreSkieursParCouleurPiste() {
        return skieurService.nombreSkieursParCouleurPiste();
    }

}