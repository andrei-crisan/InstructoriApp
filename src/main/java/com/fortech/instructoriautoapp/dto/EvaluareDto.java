package com.fortech.instructoriautoapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EvaluareDto {
    private String evaluareInstructor;
    private String numeInstructor;
    private String prenumeInstructor;
    //Todo: preluare si id - nu va fi afisat in front; poate alta ordine fielduri;
}
