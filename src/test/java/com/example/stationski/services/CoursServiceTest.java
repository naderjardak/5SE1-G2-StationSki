package com.example.stationski.services;

import com.example.stationski.StationSkiApplication;
import com.example.stationski.entities.*;
import com.example.stationski.entities.model.SkieurModel;
import com.example.stationski.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@ContextConfiguration(classes = {StationSkiApplication.class})
class CoursServiceTest {

}