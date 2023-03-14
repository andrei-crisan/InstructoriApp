package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Evaluare;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.repository.EvaluareRepository;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluareService implements iService<Evaluare> {
    @Autowired
    private EvaluareRepository evaluareRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    @Transactional
    public void create(Evaluare entity) {
        //Todo: Adaugam un review la acelasi instructor daca el exista in DB!
        Optional<Instructor> instructor = instructorRepository.findById(2L);
//        if(entity instanceof Evaluare) | poate o functie stocata in DB care returneaza un boolean, daca toat trei campuri unice
        if (instructor.isPresent()) {
            if (entity.getInstructor().equals(instructor.get())) {
                System.out.println("Inseamna ca ei DB, deci il preluam din DB");
                entity.setInstructor(instructor.get());
            }
        }
        evaluareRepository.save(entity);
        //Todo: revenire! Metodda nefinalizata!
    }

    @Override
    public List<Evaluare> readAll() {
        return evaluareRepository.findAll();
    }

    @Override
    public Evaluare read(Long idEntity) {
        Optional<Evaluare> evaluareToBeFound = evaluareRepository.findById(idEntity);
        Evaluare evaluareFoundOrNot = evaluareToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
        return evaluareFoundOrNot;
    }

    @Override
    @Transactional
    public void update(Evaluare entity) {
        Optional<Evaluare> evaluareToBeFound = evaluareRepository.findById(entity.getId());
        Evaluare evaluareFoundOrNot = evaluareToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        evaluareFoundOrNot.setInstructor(entity.getInstructor());
        evaluareFoundOrNot.setEvaluareInstructor(entity.getEvaluareInstructor());

        //Todo: Poate de implt: return entitatea stearsa!?
    }

    @Override
    @Transactional
    public void delete(Long idEntity) {
        Optional<Evaluare> evaluareToBeFound = evaluareRepository.findById(idEntity);
        Evaluare evaluareFoundOrNot = evaluareToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        evaluareRepository.delete(evaluareFoundOrNot);
    }
}
