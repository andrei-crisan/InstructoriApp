package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class Service<T> implements GenericService<T> {
    private final Repositories repositories;
    private Class<T> entityBluePrint;

    @Autowired
    public Service(WebApplicationContext applicationContext) {
        this.repositories = new Repositories(applicationContext);
    }

    @Override
    public void create(T entity) {
        repositoryProvider().save(entity);
    }

    @Override
    public List<T> readAll() {
        return repositoryProvider().findAll();
    }

    @Override
    public T read(Long idEntity) {
        Optional<T> foundOrNot = repositoryProvider().findById(idEntity);
        return foundOrNot.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
    }

    @Override
    @Transactional
    public void update(T entity) {
        repositoryProvider().save(entity);
    }

    @Override
    public void delete(Long idEntity) {
        Optional<T> entityToDelete = repositoryProvider().findById(idEntity);
        repositoryProvider().deleteById(idEntity);
        //Todo: revenim aici
    }

    public JpaRepository<T, Long> repositoryProvider() {
        Object repository = repositories.getRepositoryFor(entityBluePrint).orElseThrow(() ->
                new RepositoryException(ExceptionMessages.REPOSITORY_NOT_FOUND.errorMessage + entityBluePrint));

        return (JpaRepository<T, Long>) repository;
    }

    public void setEntityBluePrint(Class<T> entityBluePrint) {
        this.entityBluePrint = entityBluePrint;
    }
}
