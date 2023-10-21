package com.example.stationski.repositories;



import com.example.stationski.entities.Piste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PisteRepository extends JpaRepository<Piste,Integer> {
    Piste findByNumPiste(Long numPiste);


}
