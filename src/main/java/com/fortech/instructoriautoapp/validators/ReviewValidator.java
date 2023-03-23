package com.fortech.instructoriautoapp.validators;

import com.fortech.instructoriautoapp.exceptions.ValidationException;
import com.fortech.instructoriautoapp.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator implements Validator<Review> {
    private String errors = "";

    @Override
    public void validate(Review entity) {
        if (entity.getInstructorReview().length() < 2) {
            errors += "\nThe review is too short!";
        }
        if (entity.getExperienceRating() < 1 || entity.getExperienceRating() > 2) {
            errors += "\nWe are rating the experience with weird values!" + entity.getExperienceRating();
        }
           if (entity.getInstructor().getInstructorName().length() < 3) {
            errors += "\nInstructor's name is too short, less then 3 char!";
        }
        if(!entity.getInstructor().getInstructorName().matches("^[a-zA-Z]+$")){
            errors += "\nNumele nu poate fi numeric!";
        }
        if(!entity.getInstructor().getInstructorSurname().matches("^[a-zA-Z]+$")){
            errors += "\nPrenumele nu poate fi numeric!";
        }
        if (entity.getInstructor().getInstructorSurname().length() < 3) {
            errors += "\nInstructor's surname is too short, less then 3 char!";
        }
        if (entity.getInstructor().getDrivingSchool().getDrivingSchoolName().length() < 3) {
            errors += "\nThe instructor's driving school should have at least a decent name!";
        }
        if (entity.getInstructor().getDrivingSchool().getDrivingSchoolAddress().length() < 3) {
            errors += "\nThe instructor's driving school address should be reachable!";
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
