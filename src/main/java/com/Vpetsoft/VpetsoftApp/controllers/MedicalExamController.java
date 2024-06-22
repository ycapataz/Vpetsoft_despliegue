package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.MedicalExamBusiness;
import com.Vpetsoft.VpetsoftApp.dto.MedicalExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/medicalExam", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class MedicalExamController {
    @Autowired
    private MedicalExamBusiness medicalExamBusiness; // Mantenemos el nombre de la variable como MedicalExamBusiness

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllMedicalExams() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<MedicalExamDto> medicalExamDtoList = this.medicalExamBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",medicalExamDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getMedicalExamById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            MedicalExamDto medicalExamDto = medicalExamBusiness.findMedicalExamById(id);

            if (medicalExamDto != null) {
                response.put("status", "success");
                response.put("data", medicalExamDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Examen medico no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el examen medico: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createMedicalExam(@RequestBody MedicalExamDto medicalExamDto) {
        try {
            // Intentar crear el ingreso utilizando el MedicalExamBusiness
            medicalExamBusiness.createMedicalExam(medicalExamDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el examen medico se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Examen medico creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el examen medico: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMedicalExam(@PathVariable int id, @RequestBody MedicalExamDto medicalExamDto) {
        try {
            medicalExamDto.setId(id);
            // Intentar actualizar el examen medico utilizando MedicalExamBusiness
            medicalExamBusiness.updateMedicalExam(medicalExamDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el examen medico se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Examen medico actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el examen medico: " + e.getMessage());
        }
    }
}
