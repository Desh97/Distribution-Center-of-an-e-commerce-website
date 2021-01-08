package com.camels.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ManagementApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ManagementApplication.class, args);
        OGRegistration og = new OGRegistration();
        og.registerUri();
    }

}
