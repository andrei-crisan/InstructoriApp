package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public interface InstructorRepository extends Repository<Instructor, Long> {
}
