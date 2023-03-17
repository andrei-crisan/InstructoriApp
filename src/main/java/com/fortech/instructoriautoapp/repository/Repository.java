package com.fortech.instructoriautoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repository<T, Long> extends JpaRepository<T, Long> {

}
