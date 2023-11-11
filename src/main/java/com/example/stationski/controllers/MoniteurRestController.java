package com.example.stationski.controllers;

import com.example.stationski.entities.Moniteur;
import com.example.stationski.services.IMoniteurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/moniteur")
@Tag(name = "Moniteur Management")
public class MoniteurRestController {

    IMoniteurService moniteurService;
    // http://localhost:8089/stationSki/moniteur/retrieve-all-moniteurs
    @Operation(description = "liste des moniteurs")
    @GetMapping("/retrieve-all-moniteurs")
    public List<Moniteur> getAbonnements() {
        List<Moniteur> listMoniteurs = moniteurService.retrieveAllMoniteurs();
        return listMoniteurs;
    }

    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @Operation(description = "récupérer un moniteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the monitor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Moniteur.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Moniteur not found",
                    content = @Content) })
    @GetMapping("/retrieve-moniteur/{moniteur-id}")
    public Moniteur retrieveMoniteur(@Parameter(description = "id of monitor to be searched")
                                         @PathVariable("moniteur-id") Integer moniteurId) {
            return moniteurService.retrieveMoniteur(moniteurId);
}



    // http://localhost:8089/stationSki/moniteur/add-moniteur
    @Operation(description = "ajouter un moniteur")
    @PostMapping("/add-moniteur")
    public Moniteur addMoniteur(@RequestBody Moniteur m) {
        Moniteur moniteur = moniteurService.addMoniteur(m);
        return moniteur;
    }
    @Operation(description = "supprimer un moniteur")
    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-moniteur/{moniteur-id}")
    public void removeMoniteur(@PathVariable("moniteur-id") Integer moniteurId) {
        moniteurService.deleteMoniteur(moniteurId);
    }

    @Operation(description = "modifier un moniteur")
    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-moniteur")
    public Moniteur updateMoniteur(@RequestBody Moniteur m) {
        Moniteur moniteur= moniteurService.updateMoniteur(m);
        return moniteur;
    }

    @Operation(description = "ajouter un moniteur et affecter à un cours")
    // http://localhost:8089/stationSki/moniteur/addMoniteurAndAssignToCourse
    @PostMapping("/addMoniteurAndAssignToCourse")
    public Moniteur addMoniteurAndAssignToCourse(@RequestBody Moniteur m) {
        Moniteur moniteur = moniteurService.addMoniteurAndAssignToCourse(m);
        return moniteur;
    }

    // http://localhost:8089/stationSki/moniteur/bestMoniteur
    @Operation(description = "best moniteurs")
    @GetMapping("/bestMoniteur")
    public Moniteur bestMoniteur() {
        return moniteurService.bestMoniteur();
    }
}
