package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class DrivingSchoolServiceImpl implements iService<DrivingSchool> {

    private DrivingSchoolRepository drivingSchoolRepository;

    @Autowired
    public DrivingSchoolServiceImpl(DrivingSchoolRepository drivingSchoolRepository) {
        this.drivingSchoolRepository = drivingSchoolRepository;
    }

    @Override
    public void create(DrivingSchool entity) {
        boolean entityExistsInDb = drivingSchoolRepository.existsByDrivingSchoolNameAndDrivingSchoolAddress(entity.getDrivingSchoolName(), entity.getDrivingSchoolAddress());
        if (entityExistsInDb) {
            throw new ServiceException(ExceptionMessages.DRIVING_SCHOOL_WITH_GIVEN_IDENTIFIERS_ALREADY_EXISTS.errorMessage);
        }
        drivingSchoolRepository.save(entity);
    }

    @Override
    public List<DrivingSchool> readAll() {
        return drivingSchoolRepository.findAll();
    }

    @Override
    public DrivingSchool read(Long entityId) {
        Optional<DrivingSchool> drivingSchoolToBeFound = drivingSchoolRepository.findById(entityId);

        DrivingSchool drivingSchoolFoundOrNot = drivingSchoolToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.DRIVING_SCHOOL_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        return drivingSchoolFoundOrNot;
    }

    @Override
    @Transactional
    public DrivingSchool update(DrivingSchool entity) {
        Optional<DrivingSchool> scoalaToBeFound = drivingSchoolRepository.findById(entity.getId());
        DrivingSchool drivingSchoolFoundOrNot = scoalaToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.DRIVING_SCHOOL_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        drivingSchoolFoundOrNot.setDrivingSchoolName(entity.getDrivingSchoolName());
        drivingSchoolFoundOrNot.setDrivingSchoolAddress(entity.getDrivingSchoolAddress());

        if (!entity.getInstructors().isEmpty()) {
            drivingSchoolFoundOrNot.getInstructors().addAll(entity.getInstructors());
        }

        return scoalaToBeFound.get();
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<DrivingSchool> drivingSchoolInDb = drivingSchoolRepository.findById(entityId);

        DrivingSchool drivingSchoolFoundOrNot = drivingSchoolInDb.orElseThrow(() ->
                new ServiceException(ExceptionMessages.DRIVING_SCHOOL_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        drivingSchoolRepository.delete(drivingSchoolFoundOrNot);
    }
}
