package com.fortech.instructoriautoapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EvaluareDto {
    private String numeInstructor;
    private String prenumeInstructor;
    private String evaluareInstructor;
    private Integer ratingEvaluare;

    //Todo: preluare si id - nu va fi afisat in front; poate alta ordine fielduri;
}
