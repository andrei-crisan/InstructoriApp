package com.fortech.instructoriautoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InstructoriAutoAppApplication {

    public static void main(String[] args) {
        //Todo: de implementat securitate spring//resturile nu rebuie sa fie accesibile fara un auth
        //Todo: la controller scoala sa schimbam owner relatie, afisare review-uri doar la instructori, nu si la scoli.
        SpringApplication.run(InstructoriAutoAppApplication.class, args);
    }

}
