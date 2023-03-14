package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Evaluare;
import com.fortech.instructoriautoapp.model.Scoala;
import com.fortech.instructoriautoapp.repository.EvaluareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class Service<T> implements TestService<T> {
    private final Repositories repositories;

    @Autowired
    public Service(WebApplicationContext applicationContext) throws NoSuchFieldException {
        this.repositories = new Repositories(applicationContext);
    }

    @Override
    @SuppressWarnings("unchecked") //pentru a nu mai urla ca nu ii place cast JPA REPO
    public void create(T entity) {
        Object repository = repositories.getRepositoryFor(entity.getClass()).orElseThrow(() ->
                new RepositoryException(ExceptionMessages.REPOSITORY_NOT_FOUND.errorMessage + entity.getClass()));
        //Todo: poate de facut CRUD REPOS
        JpaRepository<T, Long> crudRepository = (JpaRepository<T, Long>) repository;

        crudRepository.save(entity);
    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public T read(Long idEntity) {

        return null;
    }


    @Override
    public void update(T entity) {
        Object repository = repositories.getRepositoryFor(entity.getClass()).orElseThrow(() ->
                new RepositoryException(ExceptionMessages.REPOSITORY_NOT_FOUND.errorMessage + entity.getClass()));
        //Todo: poate de facut CRUD REPOS
        JpaRepository<T, Long> crudRepository = (JpaRepository<T, Long>) repository;
        crudRepository.save(entity);

    }

    @Override
    public void delete(Long idEntity) {
        if (Service.class.isInstance(Evaluare.class)) {
            System.out.println("bauaaauaua");
        }
    }


}
