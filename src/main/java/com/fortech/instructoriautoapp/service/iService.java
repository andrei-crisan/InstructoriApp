package com.fortech.instructoriautoapp.service;

import java.util.List;

public interface iService<T>{
    void create(T entity);
    List<T> readAll();
    T read(Long idEntity);
    void update(T entity);
    void delete(Long idEntity);

    //INTERFATA PENTRU A NU AVEA BOILER PLATE CODE, CA SA PUTEM INSTANTIA PRIN INTERFATA;
    //PENTRU A TRIMITE INTERFATA CA PARAMETRU METODA GENERICA;
}
