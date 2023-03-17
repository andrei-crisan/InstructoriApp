package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import com.fortech.instructoriautoapp.service.DrivingSchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class DrivingSchoolController {
    @Autowired
    private DrivingSchoolServiceImpl drivingSchoolServiceImpl;

    @GetMapping()
    public List<DrivingSchool> getAllDrivingSchools() {
        return drivingSchoolServiceImpl.readAll();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDrivingSchool(@PathVariable Long id) {
        drivingSchoolServiceImpl.delete(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DrivingSchool> getDrivingSchoolById(@PathVariable Long id) {
         return new ResponseEntity<>(drivingSchoolServiceImpl.read(id), HttpStatus.OK);
    }

}
