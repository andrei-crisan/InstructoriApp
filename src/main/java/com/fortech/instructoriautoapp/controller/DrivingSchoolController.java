package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
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

    @GetMapping("/{id}")
    public ResponseEntity<DrivingSchool> getDrivingSchoolById(@PathVariable Long id) {
        return new ResponseEntity<>(drivingSchoolServiceImpl.read(id), HttpStatus.OK);
    }

    @PostMapping()
    public DrivingSchool saveDrivingSchool(@RequestBody DrivingSchool drivingSchool) {
        try {
            drivingSchoolServiceImpl.create(drivingSchool);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return drivingSchool;
    }

    @DeleteMapping("/rm/{id}")
    public void deleteDrivingSchool(@PathVariable Long id) {
        drivingSchoolServiceImpl.delete(id);
    }

    @PutMapping
    public ResponseEntity<DrivingSchool> updateDrivingSchool(@RequestBody DrivingSchool drivingSchool) {
        try {
            DrivingSchool updatedDrivingSchool = drivingSchoolServiceImpl.update(drivingSchool);

            return new ResponseEntity<>(updatedDrivingSchool, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}
