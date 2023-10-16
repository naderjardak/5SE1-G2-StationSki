package com.example.stationski.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkieurModels {


        private Long numSkieur;
        private String nomS;
        private String prenomS;
        private LocalDate dateNaissance;
        private String ville;
    }

