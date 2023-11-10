package com.example.stationski.controllers;

import com.example.stationski.entities.*;
import com.example.stationski.services.ICoursService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("cours/api")
public class CoursController {

    ICoursService iCoursService;
    @PostMapping(value="/addCours")
    public Cours addCours(@RequestParam Long numCours, @RequestParam TypeCours typeCours, @RequestParam Support support , @RequestParam Integer creneau, @RequestParam Integer niveau,@RequestParam Integer prix) {
        Cours cours = new Cours();

        cours.setNumCours(numCours);
        cours.setTypeCours(typeCours);
        cours.setSupport(support);
        cours.setCreneau(creneau);
        cours.setNiveau(niveau);
        cours.setPrix(prix);
        return iCoursService.addCours(cours);
    }
    @GetMapping("getAllCours")
    public List<Cours> getCours() {return iCoursService.getCours();}
    @GetMapping("getCoursById/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable("id") int idCours){return ResponseEntity.ok(iCoursService.getCoursById(idCours));}
    @GetMapping("/getcoursBytype/{typeCours}")
    public Set<Cours> getCoursByType(@PathVariable("typeCours") TypeCours typeCours) {return iCoursService.getCoursByType(typeCours);}

}
