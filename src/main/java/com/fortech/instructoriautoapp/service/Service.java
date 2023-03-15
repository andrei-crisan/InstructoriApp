package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Evaluare;
import com.fortech.instructoriautoapp.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Component
public class Service<T> implements TestService<T> {
    private final Repositories repositories;

//    @Autowired
//    List<Repo<T, Long>> lista;


    @Autowired
    public Service(WebApplicationContext applicationContext) throws NoSuchFieldException {
        this.repositories = new Repositories(applicationContext);
    }

    @Override
    @SuppressWarnings("unchecked") //pentru a nu mai urla ca nu ii place cast JPA REPO
    public void create(T entity) {
        //Todo: Metoda disticta - Un Repository Factory-> exporter
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
    public T readA(T entity){
        Object repository = repositories.getRepositoryFor(entity.getClass()).orElseThrow(() ->
                new RepositoryException(ExceptionMessages.REPOSITORY_NOT_FOUND.errorMessage + entity.getClass()));
        //Todo: poate de facut CRUD REPOS
        JpaRepository<T, Long> crudRepository = (JpaRepository<T, Long>) repository;
        return crudRepository.findById(1L).get();
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
