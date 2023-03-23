package com.fortech.instructoriautoapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDto extends BaseDto<Long>{
    private String instructorReview;
    private Integer experienceRating;
    private InstructorDto instructor;
}
