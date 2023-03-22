package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.dto.InstructorDto;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.InstructorServiceImpl;
import com.fortech.instructoriautoapp.util.InstructorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@CrossOrigin(origins = "http://localhost:4200/")
public class InstructorController {
    @Autowired
    private InstructorServiceImpl instructorServiceImpl;
    @Autowired
    private InstructorConverter instructorConverter;

    @GetMapping()
    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructorsList = instructorServiceImpl.readAll();

        List<InstructorDto> instructorDtos = instructorConverter.convertModelsToDtos(instructorsList);
        return instructorDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable Long id) {
        try {
            Instructor instructor = instructorServiceImpl.read(id);
            return new ResponseEntity<>(instructorConverter.convertModelToDto(instructor), HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<?> saveInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorConverter.convertDtoToModel(instructorDto);

        try {
            instructorServiceImpl.create(instructor);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/rm/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        try {
            instructorServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping
    public ResponseEntity<Instructor> updateInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorConverter.convertDtoToModel(instructorDto);

        try {
            Instructor updatedInstructor = instructorServiceImpl.update(instructor);
            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
