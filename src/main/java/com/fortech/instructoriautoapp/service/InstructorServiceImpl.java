package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import com.fortech.instructoriautoapp.validators.Validator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class InstructorServiceImpl implements iService<Instructor> {
    private InstructorRepository instructorRepository;
    private DrivingSchoolRepository drivingSchoolRepository;
    private Validator<Instructor> instructorValidator;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, DrivingSchoolRepository drivingSchoolRepository, Validator<Instructor> instructorValidator) {
        this.instructorRepository = instructorRepository;
        this.drivingSchoolRepository = drivingSchoolRepository;
        this.instructorValidator = instructorValidator;
    }

    @Override
    public void create(Instructor entity) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findByInstructorNameAndInstructorSurnameAndDrivingSchool_DrivingSchoolNameAndDrivingSchool_DrivingSchoolAddress(
                entity.getInstructorName(),
                entity.getInstructorSurname(),
                entity.getDrivingSchool().getDrivingSchoolName(),
                entity.getDrivingSchool().getDrivingSchoolAddress());

        if (instructorToBeFound.isPresent()) {
            throw new ServiceException(ExceptionMessages.INSTRUCTOR_WITH_THE_GIVEN_IDENTIFIERS_ALREADY_EXISTS.errorMessage);
        } else {
            Optional<DrivingSchool> drivingSchoolToBeFound = drivingSchoolRepository.findDrivingSchoolByDrivingSchoolNameAndDrivingSchoolAddress(
                    entity.getDrivingSchool().getDrivingSchoolName(),
                    entity.getDrivingSchool().getDrivingSchoolAddress());

            drivingSchoolToBeFound.ifPresent(entity::setDrivingSchool);

            instructorValidator.validate(entity);
            instructorRepository.save(entity);
        }
    }

    @Override
    public List<Instructor> readAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor read(Long entityId) {
        return instructorRepository.findById(entityId).orElseThrow(() ->
                new ServiceException(ExceptionMessages.INSTRUCTOR_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
    }

    @Override
    @Transactional
    public Instructor update(Instructor entity) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findById(entity.getId());
        Instructor instructorToBeUpdated = instructorToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.INSTRUCTOR_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        Optional<DrivingSchool> drivingSchoolToBeFound =
                drivingSchoolRepository.findDrivingSchoolByDrivingSchoolNameAndDrivingSchoolAddress(
                        entity.getDrivingSchool().getDrivingSchoolName(),
                        entity.getDrivingSchool().getDrivingSchoolAddress());

        instructorToBeUpdated.setInstructorName(entity.getInstructorName());
        instructorToBeUpdated.setInstructorSurname(entity.getInstructorSurname());

        if (drivingSchoolToBeFound.isPresent()) {
            instructorToBeUpdated.setDrivingSchool(drivingSchoolToBeFound.get());
        } else {
            instructorToBeUpdated.setDrivingSchool(entity.getDrivingSchool());
        }

        return instructorToBeUpdated;
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(entityId);

        Instructor instructorFoundOrNot = instructorToDelete.orElseThrow(() ->
                new ServiceException(ExceptionMessages.INSTRUCTOR_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        instructorRepository.delete(instructorFoundOrNot);
    }
}
