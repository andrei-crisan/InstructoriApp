package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.service.DrivingSchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
        try {
            DrivingSchool drivingSchool = drivingSchoolServiceImpl.read(id);

            return new ResponseEntity<>(drivingSchool, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<?> saveDrivingSchool(@RequestBody DrivingSchool drivingSchool) {
        try {
            drivingSchoolServiceImpl.create(drivingSchool);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/rm/{id}")
    public ResponseEntity<?> deleteDrivingSchool(@PathVariable Long id) {
        try {
            drivingSchoolServiceImpl.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
