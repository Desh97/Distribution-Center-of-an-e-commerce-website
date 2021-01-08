package com.camels.order_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OrderGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderGeneratorApplication.class, args);
        try {
            ClockRegistration clock = new ClockRegistration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
