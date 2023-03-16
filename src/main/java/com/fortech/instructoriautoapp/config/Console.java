package com.fortech.instructoriautoapp.config;

import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Evaluare;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Scoala;
import com.fortech.instructoriautoapp.service.Service;
import com.fortech.instructoriautoapp.service.iService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Console {
    @Bean
    CommandLineRunner commandLineRunner(Service<Evaluare> test, iService<Evaluare> evaluareService, iService<Instructor> instructorService, iService<Scoala> scoalaService) {
        return args -> {
            Scoala scoala = new Scoala();
            scoala.setNumeScoala("Tony Auto");
            scoala.setAdresaScoala("Drr, SV");

            Instructor instructor = new Instructor();
            instructor.setNumeInstructor("Andri ");
            instructor.setPrenumeInstructor("Crisan");
            instructor.getListaScoliSoferi().add(scoala);
            Evaluare eval = new Evaluare();

            Evaluare evaluare = new Evaluare();
            evaluare.setEvaluareInstructor("Un instructor slab");
            evaluare.setRatingEvaluare(0);
            evaluare.setInstructor(instructor);



        try {
            test.setEntityBluePrint(Evaluare.class);
            test.create(evaluare);
        }catch (RepositoryException e){
            e.printStackTrace();
        }

//            instructorService.updateInstructor();

//            //adauga scaoala
//            Scoala scoalaNoua = new Scoala();
//            Instructor instructorNou = new Instructor();
//            instructorNou.setNumeInstructor("Heroium");
//            instructorNou.setPrenumeInstructor("Sorin");
//            instructorNou.getListaScoliSoferi().add(scoalaNoua);
//
//            scoalaNoua.setNumeScoala("Tamtam");
//            scoalaNoua.setAdresaScoala("Grigorescu");
//            scoalaNoua.getListaInstructori().add(instructorNou);
//
//            scoalaService.adaugaScoala(scoalaNoua);


            //sterge evaluare - merge;
//            instructorService.stergeInstructor();


//            Scoala scoalaUpdate = new Scoala();
//
//            scoalaUpdate.setId(2L);
//            scoalaUpdate.setAdresaScoala("Cojocnei");
//
//            scoalaService.update(scoalaUpdate);


        };

    }
}
