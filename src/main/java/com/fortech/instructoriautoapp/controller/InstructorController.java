package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructori")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping()
    public List<Instructor> getAllInstructori() {
        return instructorService.readAll();
    }
    @PostMapping()
    public Instructor addInstructor(@RequestBody Instructor instructor){
        instructorService.create(instructor);
        return instructor;
    }
    @DeleteMapping("/sterge/{id}")
    public void deleteInstructor(@PathVariable Long id){
        instructorService.delete(id);
    }
}
