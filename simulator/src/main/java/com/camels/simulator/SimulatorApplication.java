package com.camels.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SimulatorApplication {



    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);
        try {
            ClockRegistration clock = new ClockRegistration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

