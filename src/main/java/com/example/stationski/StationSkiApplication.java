package com.example.stationski;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class StationSkiApplication {

    final static Logger logger = LoggerFactory.getLogger(StationSkiApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(StationSkiApplication.class, args);
    }

    @GetMapping("/something")
    public ResponseEntity<String> createLogs() {
        logger.warn("Just checking");
        return ResponseEntity.ok().body("All Ok");
    }

}
