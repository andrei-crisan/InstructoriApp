package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.Evaluare;
import org.springframework.stereotype.Component;

@Component("evaluareRepository")
public interface EvaluareRepository extends Repository<Evaluare, Long> {
}
