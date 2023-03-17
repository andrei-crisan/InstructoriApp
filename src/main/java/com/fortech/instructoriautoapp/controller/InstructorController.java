package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.InstructorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private InstructorServiceImpl instructorServiceImpl;

    @GetMapping()
    public List<Instructor> getAllInstructors() {
        return instructorServiceImpl.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorServiceImpl.read(id), HttpStatus.OK);
    }

    @PostMapping()
    public Instructor saveInstructor(@RequestBody Instructor instructor) {
        instructorServiceImpl.create(instructor);
        return instructor;
    }

    @DeleteMapping("/rm/{id}")
    public void deleteInstructor(@PathVariable Long id) {
        instructorServiceImpl.delete(id);
    }

    @PutMapping
    public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor instructor) {
        try {
            Instructor updatedInstructor = instructorServiceImpl.update(instructor);

            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}
