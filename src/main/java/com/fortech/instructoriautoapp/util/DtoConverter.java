package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.DrivingSchoolDto;
import com.fortech.instructoriautoapp.dto.InstructorDto;
import com.fortech.instructoriautoapp.dto.ReviewDto;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Review;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class DtoConverter {

//    public InstructorDto convertModelToDto(Instructor instructor){
//        InstructorDto instructorDto = new InstructorDto(
//                instructor.getId(),
//                instructor.getInstructorName(),
//                instructor.getInstructorSurname(),
//                instructor.getDrivingSchool().getDrivingSchoolName(),
//                instructor.getDrivingSchool().getDrivingSchoolAddress(),
//                instructor.getReviews());
//        return instructorDto;
//    }

    public Instructor convertDtoToModel(InstructorDto instructorDto){
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.setDrivingSchoolName(instructorDto.getDrivingSchoolName());
        drivingSchool.setDrivingSchoolAddress(instructorDto.getDrivingSchoolAddress());

        Instructor instructor = new Instructor();

        instructor.setId(instructorDto.getId());
        instructor.setInstructorName(instructorDto.getInstructorName());
        instructor.setInstructorSurname(instructorDto.getInstructorSurname());
        instructor.setDrivingSchool(drivingSchool);
        instructor.setReviews(instructorDto.getReviews());

        return instructor;
    }
//    public List<InstructorDto> convertModelsToDtos(Collection<Instructor> models) {
//        return models.stream()
//                .map(model -> convertModelToDto(model))
//                .collect(Collectors.toList());
//    }

}
