package com.example.stationski;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class StationSkiApplication {
    @Bean
    public ModelMapper modelMapper(){
      return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(StationSkiApplication.class, args);
    }

}
