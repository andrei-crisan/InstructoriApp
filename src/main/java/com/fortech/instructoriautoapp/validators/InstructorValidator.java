package com.fortech.instructoriautoapp.validators;

import com.fortech.instructoriautoapp.exceptions.ValidationException;
import com.fortech.instructoriautoapp.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorValidator implements Validator<Instructor> {
    private String errors = "";

    @Override
    public void validate(Instructor entity) {
        if (entity.getInstructorName().length() < 3) {
            errors += "\nInstructor's name is less then 3 char.";
        }
        if (entity.getInstructorSurname().length() < 3) {
            errors += "\nInstructor's surname is less then 3 char.";
        }
        if (entity.getDrivingSchool().getDrivingSchoolName().length() < 3) {
            errors += "\nDriving school's name is less then 3 char.";
        }
        if (entity.getDrivingSchool().getDrivingSchoolAddress().length() < 3) {
            errors += "\nDriving school's address is less then 3 char.";
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
