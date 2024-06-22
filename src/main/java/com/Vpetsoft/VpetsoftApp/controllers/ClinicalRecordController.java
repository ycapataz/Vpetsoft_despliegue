package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.ClinicalRecordBusiness;
import com.Vpetsoft.VpetsoftApp.dto.ClinicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicalrecords")
@CrossOrigin("*")
public class ClinicalRecordController {

    @Autowired
    private ClinicalRecordBusiness clinicalRecordBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<ClinicalRecordDto>> findAllClinicalRecords() {
        try {
            List<ClinicalRecordDto> clinicalRecordDtos = clinicalRecordBusiness.findAll();
            return ResponseEntity.ok(clinicalRecordDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<ClinicalRecordDto> findClinicalRecordById(@PathVariable int id) {
        try {
            ClinicalRecordDto clinicalRecordDto = clinicalRecordBusiness.findClinicalRecordById(id);
            if (clinicalRecordDto != null) {
                return ResponseEntity.ok(clinicalRecordDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createClinicalRecord(@RequestBody ClinicalRecordDto clinicalRecordDto) {
        try {
            clinicalRecordBusiness.createClinicalRecord(clinicalRecordDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro Clinico Creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el registro clínico: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateClinicalRecord(@PathVariable int id, @RequestBody ClinicalRecordDto clinicalRecordDto) {
        try {
            clinicalRecordDto.setId(id);
            String message = clinicalRecordBusiness.updateClinicalRecord(clinicalRecordDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el registro clínico: " + e.getMessage());
        }
    }
}
