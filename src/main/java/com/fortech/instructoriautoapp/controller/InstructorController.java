package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.dto.InstructorDto;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.exceptions.ValidationException;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.InstructorServiceImpl;
import com.fortech.instructoriautoapp.service.iService;
import com.fortech.instructoriautoapp.util.InstructorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private iService<Instructor> instructorServiceImpl;
    private InstructorConverter instructorConverter;

    @Autowired
    public InstructorController(InstructorServiceImpl instructorServiceImpl, InstructorConverter instructorConverter) {
        this.instructorServiceImpl = instructorServiceImpl;
        this.instructorConverter = instructorConverter;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<InstructorDto>> getAllInstructors() {
        List<Instructor> instructorsList = instructorServiceImpl.readAll();

        return new ResponseEntity<>(instructorConverter.convertModelsToDtos(instructorsList), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> saveInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorConverter.convertDtoToModel(instructorDto);

        try {
            instructorServiceImpl.create(instructor);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | ValidationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
