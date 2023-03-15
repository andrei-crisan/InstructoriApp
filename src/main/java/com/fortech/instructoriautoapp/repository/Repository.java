package com.fortech.instructoriautoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

@NoRepositoryBean
@Component
public interface Repository<T, Long> extends JpaRepository<T, Long> {
}
