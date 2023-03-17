package com.fortech.instructoriautoapp.config;

import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.service.ServiceImpl;
import com.fortech.instructoriautoapp.service.iService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Console {
    @Bean
    CommandLineRunner commandLineRunner(ServiceImpl<Review> test, iService<Review> reviewService, iService<Instructor> instructorService, iService<DrivingSchool> scoalaService) {
        return args -> {
            DrivingSchool drivingSchool = new DrivingSchool();
            drivingSchool.setDrivingSchoolName("Tony Auto");
            drivingSchool.setDrivingSchoolAddress("Drr, SV");

            Instructor instructor = new Instructor();
            instructor.setInstructorName("Andri ");
            instructor.setInstructorSurname("Crisan");
            instructor.getDrivingSchools().add(drivingSchool);
            Review eval = new Review();

            Review review = new Review();
            review.setInstructorReview("Un instructor slab_02");
            review.setExperienceRating(0);
            review.setInstructor(instructor);



        try {
////            test.setEntityBluePrint(Evaluare.class);
//            test.create(evaluare);
            reviewService.create(review);
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
