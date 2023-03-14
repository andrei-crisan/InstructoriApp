package com.fortech.instructoriautoapp.service;

import java.util.List;

public interface TestService<T>{
    void create(T entity);
    List<T> readAll();
    T read(Long idEntity);
    void update(T entity);
    void delete(Long idEntity);
}
