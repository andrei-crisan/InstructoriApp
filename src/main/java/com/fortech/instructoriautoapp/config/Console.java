package com.fortech.instructoriautoapp.config;

import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.service.iService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

@Configuration
public class Console {
    @Bean
    CommandLineRunner commandLineRunner(iService<Review> reviewService, iService<Instructor> instructorService, iService<DrivingSchool> scoalaService) {
        return args -> {
            DrivingSchool drivingSchool = new DrivingSchool();
            drivingSchool.setDrivingSchoolName("ACR Auto");
            drivingSchool.setDrivingSchoolAddress("AltaAdresas, SV");
//
            Instructor instructor = new Instructor();
            instructor.setInstructorName("Vasile");
            instructor.setInstructorSurname("Lombrea");
            instructor.setDrivingSchool(drivingSchool);

            Review review = new Review();
            review.setInstructorReview("lombritza nou!!");
            review.setExperienceRating(2);
            review.setInstructor(instructor);



        try {
////            test.setEntityBluePrint(Evaluare.class);
//            test.create(evaluare);
            reviewService.create(review);
        }catch (DataIntegrityViolationException e){
            System.out.println("Eceptie prinsa!!");
            e.printStackTrace();
        }


        };

    }
}
