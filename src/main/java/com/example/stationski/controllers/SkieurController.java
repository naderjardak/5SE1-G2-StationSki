package com.example.stationski.controllers;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.ISkieurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/skieur")
public class SkieurController {
    ISkieurService skieurService;
    @PutMapping("/assignSkieurToPiste/{numSkieur}/{numPiste}")
    public Skieur assignSkieurToPiste(@PathVariable("numSkieur")Long numSkieur,
                                        @PathVariable("numPiste")Long numPiste) {
        return skieurService.assignSkieurToPiste(numSkieur,numPiste);
    }

    @PostMapping("/addSkieurAndAssignToCourse/{numCours}")
    public Skieur addSkieurAndAssignToCourse(@RequestBody SkieurModel skieurM,
                                             @PathVariable("numCours")Long numCours) {
        return skieurService.addSkieurAndAssignToCourse(skieurM,numCours);
    }


    @GetMapping("/retrieveSkieursByTypeAbonnement/{typeAbonnement}")
    public List<Skieur> retrieveSkieursByTypeAbonnement(@PathVariable("typeAbonnement") TypeAbonnement typeAbonnement) {
        return skieurService.retrieveSkieursByTypeAbonnement(typeAbonnement);
    }

    @GetMapping("/nombreSkieursParCouleurPiste")
    public Map<Couleur,Integer> nombreSkieursParCouleurPiste() {
        return skieurService.nombreSkieursParCouleurPiste();
    }

}
