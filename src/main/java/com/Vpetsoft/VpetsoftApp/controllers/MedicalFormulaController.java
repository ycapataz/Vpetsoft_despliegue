package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.MedicalFormulaBusiness;
import com.Vpetsoft.VpetsoftApp.dto.MedicalFormulaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/medicalFormula", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class MedicalFormulaController {
    @Autowired
    private MedicalFormulaBusiness medicalFormulaBusiness; // Mantenemos el nombre de la variable como MedicalFormulaImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllMedicalFormulas() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<MedicalFormulaDto> medicalFormulaDtoList = this.medicalFormulaBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",medicalFormulaDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getMedicalFormulaById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            MedicalFormulaDto medicalFormulaDto = medicalFormulaBusiness.findMedicalFormulaById(id);

            if (medicalFormulaDto != null) {
                response.put("status", "success");
                response.put("data", medicalFormulaDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Formula medica no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la formula medica: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createMedicalFormula(@RequestBody MedicalFormulaDto medicalFormulaDto) {
        try {
            // Intentar crear el ingreso utilizando el MedicalExamBusiness
            medicalFormulaBusiness.createMedicalFormula(medicalFormulaDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el examen medico se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Formula medica creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la formula medica: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMedicalFormula(@PathVariable int id, @RequestBody MedicalFormulaDto medicalFormulaDto) {
        try {
            medicalFormulaDto.setId(id);
            // Intentar actualizar el examen medicó utilizando MedicalExamBusiness
            medicalFormulaBusiness.updateMedicalFormula(medicalFormulaDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el examen medico se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Formula medica actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la formula medica: " + e.getMessage());
        }
    }
}
