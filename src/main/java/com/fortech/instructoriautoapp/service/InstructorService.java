package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Scoala;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService implements iService<Instructor> {
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public void create(Instructor entity) {
        instructorRepository.save(entity);
    }

    @Override
    public List<Instructor> readAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor read(Long idEntity) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findById(idEntity);
        Instructor instructorFoundOrNot = instructorToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
        return instructorFoundOrNot;
    }

    @Override
    @Transactional
    public void update(Instructor entity) {
        Instructor instructorForUpdate = instructorRepository.findById(1L).get();
        List<Scoala> listaNoua = instructorForUpdate.getListaScoliSoferi();

        Scoala scoala1 = new Scoala();
        scoala1.setNumeScoala("Timbuktu");
        Scoala scoala2 = new Scoala();
        scoala2.setNumeScoala("Ilie");
        Scoala scoala3 = new Scoala();
        scoala3.setNumeScoala("Pisica");

        listaNoua.add(scoala2);
        listaNoua.add(scoala1);
        listaNoua.add(scoala3);

        instructorForUpdate.setListaScoliSoferi(listaNoua);
        //Todo: metoda nefinalizata | testing ongoing...
    }

    @Override
    @Transactional
    public void delete(Long idEntity) {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(idEntity);
        Instructor instructorFoundOrNot = instructorToDelete.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        instructorRepository.delete(instructorFoundOrNot);
    }
}
