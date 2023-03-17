package com.fortech.instructoriautoapp.service;

import java.util.List;

public interface iService<T>{
    void create(T entity);
    List<T> readAll();
    T read(Long entityId);
    T update(T entity);
    void delete(Long entityId);

}
