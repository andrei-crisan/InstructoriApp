package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
