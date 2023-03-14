package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.model.Scoala;
import com.fortech.instructoriautoapp.service.ScoalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scoli")
public class ScoalaController {
    @Autowired
    private ScoalaService scoalaService;

    @GetMapping()
    public List<Scoala> getAllScoli() {
        return scoalaService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scoala> getScoalaById(@PathVariable Long id) {
        return new ResponseEntity<>(scoalaService.read(id), HttpStatus.OK);
    }

}
