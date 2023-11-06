package com.example.stationski.controllers;

import com.example.stationski.entities.*;
import com.example.stationski.services.ICoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public class CoursController {
    @Autowired
    ICoursService iCoursService;
    @PostMapping(value="addCours")
    Cours addCours(@RequestParam Long numCours, @RequestParam TypeCours typeCours, @RequestParam Support support , @RequestParam Integer creneau) {
        Cours cours = new Cours();

        cours.setNumCours(numCours);
        cours.setTypeCours(typeCours);
        cours.setSupport(support);
        cours.setCreneau(creneau);
        return iCoursService.addCours(cours);
    }
    @GetMapping("getCoursById/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable("id") int idCours){
        return ResponseEntity.ok(iCoursService.getCoursById(idCours));
    }
    @GetMapping("/retrieve-all-coursBytype/{typecours}")
    public Set<Cours> getCoursByType(@PathVariable("typeAbonnement") TypeCours typeCours) {
        return iCoursService.getCoursByType(typeCours);
    }
}
