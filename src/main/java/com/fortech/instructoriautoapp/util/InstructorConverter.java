package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.InstructorDto;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorConverter extends BaseConverter<Instructor, InstructorDto> {
    @Override
    public Instructor convertDtoToModel(InstructorDto dto) {
        DrivingSchool drivingSchool = DrivingSchool.builder()
                .drivingSchoolName(dto.getDrivingSchoolName())
                .drivingSchoolAddress(dto.getDrivingSchoolAddress())
                .build();
        //Todo: Album ID?

        Instructor instructor = Instructor.builder()
                .instructorName(dto.getInstructorName())
                .instructorSurname(dto.getInstructorSurname())
                .drivingSchool(drivingSchool)
                .reviews(dto.getReviews())
                .build();
        instructor.setId(dto.getId());

        return instructor;
    }

    @Override
    public InstructorDto convertModelToDto(Instructor instructor) {
        InstructorDto instructorDto = InstructorDto.builder()
                .instructorName(instructor.getInstructorName())
                .instructorSurname(instructor.getInstructorSurname())
                .drivingSchoolName(instructor.getDrivingSchool().getDrivingSchoolName())
                .drivingSchoolAddress(instructor.getDrivingSchool().getDrivingSchoolAddress())
                .reviews(instructor.getReviews())
                .build();
        instructorDto.setId(instructor.getId());

        return instructorDto;
    }
}
