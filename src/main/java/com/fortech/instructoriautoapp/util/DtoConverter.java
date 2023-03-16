package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.EvaluareDto;
import com.fortech.instructoriautoapp.model.Evaluare;
import org.springframework.stereotype.Component;


@Component
public class DtoConverter {

    public EvaluareDto evaluareToDto(Evaluare evaluare){
        return new EvaluareDto(evaluare.getEvaluareInstructor(),
                evaluare.getInstructor().getNumeInstructor(),
                evaluare.getInstructor().getPrenumeInstructor(), evaluare.getRatingEvaluare());
    }
    //Todo: Converter from DTO to Evaluare and other entities;

}
