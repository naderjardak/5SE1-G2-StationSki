package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class AbonnementRepositoryTest {
    @Autowired
    AbonnementRepository ar;

    static Abonnement a=Abonnement.builder().numAbon(10L).prixAbon(15F).typeAbon(TypeAbonnement.ANNUEL).build();
    @Test
    @Order(0)
    public void addAbonnement(){
     a =ar.save(a);
        Assertions.assertNotNull(a.getIdAbonnement());
    }
    @Test
    @Order(1)
    public void getAllAbonnements(){
        List<Abonnement>abonnementList=ar.findAll();
        Assertions.assertFalse(0>abonnementList.size());
    }
    @Test
    @Order(2)
    public void deleteAbonnements() {
        ar.delete(a);
        Optional<Abonnement> deletedAbonnement = ar.findById(a.getIdAbonnement());
        Assertions.assertNull(deletedAbonnement, "L'abonnement ne devrait pas être trouvé après la suppression");
    }

}
