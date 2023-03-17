package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class DrivingSchoolController {
//    @Autowired
//    private ScoalaService scoalaService;

    @Autowired
    private ServiceImpl<DrivingSchool> drivingSchoolServiceImpl;

    @GetMapping()
    public List<DrivingSchool> getAllDrivingSchools() {
        drivingSchoolServiceImpl.setEntityBluePrint(DrivingSchool.class);
        return drivingSchoolServiceImpl.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrivingSchool> getDrivingSchoolById(@PathVariable Long id) {
        drivingSchoolServiceImpl.setEntityBluePrint(DrivingSchool.class);
        return new ResponseEntity<>(drivingSchoolServiceImpl.read(id), HttpStatus.OK);
    }

}
