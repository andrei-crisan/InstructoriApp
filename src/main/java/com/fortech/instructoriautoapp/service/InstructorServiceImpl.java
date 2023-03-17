package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements iService<Instructor> {
    @Autowired
    private Repository<Instructor, Long> instructorRepository;

    @Override
    public void create(Instructor entity) {
        instructorRepository.save(entity);
    }

    @Override
    public List<Instructor> readAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor read(Long entityId) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findById(entityId);
        Instructor instructorFoundOrNot = instructorToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
        return instructorFoundOrNot;
    }

    @Override
    @Transactional
    public Instructor update(Instructor entity) {
        Instructor instructorForUpdate = instructorRepository.findById(1L).get();
        List<DrivingSchool> listaNoua = instructorForUpdate.getDrivingSchools();

        DrivingSchool drivingSchool1 = new DrivingSchool();
        drivingSchool1.setDrivingSchoolName("Timbuktu");
        DrivingSchool drivingSchool2 = new DrivingSchool();
        drivingSchool2.setDrivingSchoolName("Ilie");
        DrivingSchool drivingSchool3 = new DrivingSchool();
        drivingSchool3.setDrivingSchoolName("Pisica");

        listaNoua.add(drivingSchool2);
        listaNoua.add(drivingSchool1);
        listaNoua.add(drivingSchool3);

        instructorForUpdate.setDrivingSchools(listaNoua);
        //Todo: metoda nefinalizata | testing ongoing...
        return null;
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(entityId);

        Instructor instructorFoundOrNot = instructorToDelete.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        instructorRepository.delete(instructorFoundOrNot);
    }
}
