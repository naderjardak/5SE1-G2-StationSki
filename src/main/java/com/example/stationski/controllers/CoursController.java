package com.example.stationski.controllers;

import com.example.stationski.entities.*;
import com.example.stationski.services.ICoursService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("cours")
public class CoursController {
    @Autowired
    ICoursService iCoursService;
    @PostMapping(value="addCours")
    public Cours addCours(@RequestParam Long numCours, @RequestParam TypeCours typeCours, @RequestParam Support support , @RequestParam Integer creneau) {
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
