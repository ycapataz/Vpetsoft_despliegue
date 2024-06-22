package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.DiseaseBusiness;
import com.Vpetsoft.VpetsoftApp.dto.DiseaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
@CrossOrigin("*")
public class DiseaseController {

    @Autowired
    private DiseaseBusiness diseaseBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<DiseaseDto>> findAllDiseases() {
        try {
            List<DiseaseDto> diseaseDtos = diseaseBusiness.findAll();
            return ResponseEntity.ok(diseaseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<DiseaseDto> findDiseaseById(@PathVariable int id) {
        try {
            DiseaseDto diseaseDto = diseaseBusiness.findDiseaseById(id);
            if (diseaseDto != null) {
                return ResponseEntity.ok(diseaseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDisease(@RequestBody DiseaseDto diseaseDto) {
        try {
            String message = diseaseBusiness.createDisease(diseaseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la enfermedad: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDisease(@PathVariable int id, @RequestBody DiseaseDto diseaseDto) {
        try {
            String message = diseaseBusiness.updateDisease(diseaseDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la enfermedad: " + e.getMessage());
        }
    }
}
