package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.DrivingSchool;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DrivingSchoolRepository extends Repository<DrivingSchool, Long> {
    Optional<DrivingSchool> findDrivingSchoolByDrivingSchoolNameAndDrivingSchoolAddress(String drivingSchoolName, String drivingSchoolAddress);

    boolean existsByDrivingSchoolNameAndDrivingSchoolAddress(String drivingSchoolName, String drivingSchoolAddress);
}
