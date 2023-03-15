package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.InstructorService;
import com.fortech.instructoriautoapp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructori")
public class InstructorController {
//    @Autowired
//    private InstructorService instructorService;
    @Autowired
    private Service<Instructor> instructorService;

    @GetMapping()
    public List<Instructor> getAllInstructori() {
        instructorService.setEntityBluePrint(Instructor.class);
        return instructorService.readAll();
    }
    @PostMapping()
    public Instructor addInstructor(@RequestBody Instructor instructor){
        instructorService.setEntityBluePrint(Instructor.class);
        instructorService.create(instructor);
        return instructor;
    }
    @DeleteMapping("/sterge/{id}")
    public void deleteInstructor(@PathVariable Long id){
        instructorService.setEntityBluePrint(Instructor.class);
        instructorService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getEvaluareById(@PathVariable Long id) {
        instructorService.setEntityBluePrint(Instructor.class);
        return new ResponseEntity<>(instructorService.read(id), HttpStatus.OK);
    }

}
