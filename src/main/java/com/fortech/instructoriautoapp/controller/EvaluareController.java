package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.dto.EvaluareDto;
import com.fortech.instructoriautoapp.model.Evaluare;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.service.EvaluareService;
import com.fortech.instructoriautoapp.service.Service;
import com.fortech.instructoriautoapp.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/evaluari")
public class EvaluareController {

//    @Autowired
//    private EvaluareService evaluareService; // TODO: interfete pentru low coupling

    @Autowired
    private Service<Evaluare> evaluareService;

    @Autowired
    private DtoConverter dtoConverter;


    @GetMapping()
    public List<Evaluare> getAllEvaluari() {
        evaluareService.setEntityBluePrint(Evaluare.class);
        return evaluareService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluare> getEvaluareById(@PathVariable Long id) {
        evaluareService.setEntityBluePrint(Evaluare.class);
        return new ResponseEntity<>(evaluareService.read(id), HttpStatus.OK);
    }

    @GetMapping("/dto/{id}")
    //Todo: Test method!!!
    public ResponseEntity<EvaluareDto> getEvaluareByIdDto(@PathVariable Long id) {
        Evaluare evaluare = evaluareService.read(id);
        return new ResponseEntity<>(dtoConverter.evaluareToDto(evaluare), HttpStatus.OK);
    }

    @DeleteMapping("/sterge/{id}")
    public void deleteEvaluare(@PathVariable Long id) {
        evaluareService.setEntityBluePrint(Evaluare.class);
        evaluareService.delete(id);
    }

    @PostMapping()
    public Evaluare addEvaluare(@RequestBody Evaluare evaluare) {
        evaluareService.setEntityBluePrint(Evaluare.class);
        evaluareService.create(evaluare);
        return evaluare;
    }

}
