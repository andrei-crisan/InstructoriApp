package com.fortech.instructoriautoapp.repository;


import com.fortech.instructoriautoapp.model.ERole;
import com.fortech.instructoriautoapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
