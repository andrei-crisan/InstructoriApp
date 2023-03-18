package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements iService<Instructor> {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private DrivingSchoolRepository drivingSchoolRepository;

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
            instructorRepository.save(entity);
        }
    }

    @Override
    public List<Instructor> readAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor read(Long entityId) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findById(entityId);
        Instructor instructorFoundOrNot = instructorToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        return instructorFoundOrNot;
    }

    @Override
    @Transactional
    public Instructor update(Instructor entity) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findById(entity.getId());
        Instructor instructorToBeUpdated = instructorToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        Optional<DrivingSchool> drivingSchoolToBeFound =
                drivingSchoolRepository.findById(entity.getDrivingSchool().getId());

        instructorToBeUpdated.setInstructorName(entity.getInstructorName());
        instructorToBeUpdated.setInstructorSurname(entity.getInstructorSurname());
        if (drivingSchoolToBeFound.isPresent()) {
            instructorToBeUpdated.setDrivingSchool(drivingSchoolToBeFound.get());
        } else {
            instructorToBeUpdated.setDrivingSchool(drivingSchoolToBeFound.get());
        }

        return instructorToBeUpdated;
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(entityId);

        Instructor instructorFoundOrNot = instructorToDelete.orElseThrow(() ->
                new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        instructorRepository.delete(instructorFoundOrNot);
    }
}
