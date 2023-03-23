package com.fortech.instructoriautoapp.validators;

import com.fortech.instructoriautoapp.exceptions.ValidationException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import org.springframework.stereotype.Component;

@Component
public class DrivingSchooValidator implements Validator<DrivingSchool> {
    private String errors = "";

    @Override
    public void validate(DrivingSchool drivingSchool) {
        if (drivingSchool.getDrivingSchoolName().length() < 3) {
            errors += "\nDriving school's name is less then 3 char.";
        }

        if (drivingSchool.getDrivingSchoolAddress().length() < 3) {
            errors += "\nDriving school's address is less then 3 char.";
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
