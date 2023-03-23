package com.fortech.instructoriautoapp.dto;

import com.fortech.instructoriautoapp.model.Review;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InstructorDto extends BaseDto<Long>{
    private String instructorName;
    private String instructorSurname;
    private String drivingSchoolName;
    private String drivingSchoolAddress;
    private List<Review> reviews;
}
