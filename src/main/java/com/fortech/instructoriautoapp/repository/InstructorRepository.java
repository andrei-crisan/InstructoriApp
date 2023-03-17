package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface InstructorRepository extends Repository<Instructor, Long> {
    boolean existsByInstructorNameAndInstructorSurname(
            String instructorName, String instructorSurname);
    Optional<Instructor> findByInstructorNameAndInstructorSurnameAndDrivingSchool_DrivingSchoolNameAndDrivingSchool_DrivingSchoolAddress(String instructorName, String instructorSurname, String drivingSchoolName, String drivingSchoolAddress);
    Optional<Instructor> findInstructorByInstructorSurname(String instructorSurname);
    Optional<Instructor> findInstructorByInstructorNameAndInstructorSurname(String instructorName, String instructorSurname);
}
