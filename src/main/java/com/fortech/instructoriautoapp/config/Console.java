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
    CommandLineRunner commandLineRunner(iService<Review> reviewService, iService<Instructor> instructorService, iService<DrivingSchool> drivingSchoolService) {
        return args -> {
            DrivingSchool drivingSchool = new DrivingSchool();
            drivingSchool.setDrivingSchoolName("Ralcua Auto");
            drivingSchool.setDrivingSchoolAddress("ARuncuta, SV");
//            drivingSchool.setId(4L);
//
            Instructor instructor = new Instructor();
            instructor.setInstructorName("Vasile");
            instructor.setInstructorSurname("Babuin");
//            instructor.setId(5L);
//            instructor.setDrivingSchool(drivingSchool);

//            Review review = new Review();
//            review.setInstructorReview("Eval_baisoara!");
//            review.setExperienceRating(2);
//            review.setInstructor(instructor);




        try {
////            test.setEntityBluePrint(Evaluare.class);
//            test.create(evaluare);
//            reviewSaervice.create(review);
//            instructorService.update(instructor);
//            drivingSchoolService.create(drivingSchool);
//            instructorService.create(instructor);
        }catch (DataIntegrityViolationException e){
            System.out.println("Eceptie prinsa!!");
            e.printStackTrace();
        }


        };

    }
}
