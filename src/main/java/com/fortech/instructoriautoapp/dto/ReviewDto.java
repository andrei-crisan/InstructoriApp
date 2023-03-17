package com.fortech.instructoriautoapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ReviewDto {
    private String nameInstructor;
    private String surnameInstructor;
    private String reviewInstructor;
    private Integer reviewRating;

    //Todo: preluare si id - nu va fi afisat in front; poate alta ordine fielduri;
}
