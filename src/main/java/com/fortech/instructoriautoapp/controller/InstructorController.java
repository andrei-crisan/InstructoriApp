package com.fortech.instructoriautoapp.controller;

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

    @PostMapping()
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        instructorServiceImpl.create(instructor);
        return instructor;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInstructor(@PathVariable Long id) {
        instructorServiceImpl.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorServiceImpl.read(id), HttpStatus.OK);
    }

}
