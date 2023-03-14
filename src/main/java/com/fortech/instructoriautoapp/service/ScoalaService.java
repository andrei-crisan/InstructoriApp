package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Scoala;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import com.fortech.instructoriautoapp.repository.ScoalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ScoalaService implements iService<Scoala> {
    @Autowired
    private ScoalaRepository scoalaRepository;

    @Override
    public void create(Scoala entity) {
        //Todo: verifica daca entitatea nu e null; si arunca exceptie;
        scoalaRepository.save(entity);
    }

    @Override
    public List<Scoala> readAll() {
        return scoalaRepository.findAll();
    }

    //Todo: boiler plate/duplicate code
    @Override
    public Scoala read(Long idEntity) {
        Optional<Scoala> scoalaToBeFound = scoalaRepository.findById(idEntity);
        Scoala scoalaFoundOrNot = scoalaToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        return scoalaFoundOrNot;
    }

    @Override
    @Transactional
    public void update(Scoala entity) {
        Optional<Scoala> scoalaToBeFound = scoalaRepository.findById(entity.getId());
        Scoala scoalaFoundOrNot = scoalaToBeFound.orElseThrow(() -> new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        //Todo:Atentie, aici folosim id la update citire scoala in cod
        scoalaFoundOrNot.setNumeScoala(entity.getNumeScoala());
        scoalaFoundOrNot.setAdresaScoala(entity.getAdresaScoala());

        if (!entity.getListaInstructori().isEmpty()) {
            scoalaFoundOrNot.getListaInstructori().addAll(entity.getListaInstructori());
        }
    }

    @Override
    @Transactional
    public void delete(Long idEntity) {
        Optional<Scoala> scoalaToBeFound = scoalaRepository.findById(idEntity);
        Scoala scoalaFoundOrNot = scoalaToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        scoalaRepository.delete(scoalaFoundOrNot);
    }
}
