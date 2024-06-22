package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.OutptTypeBusiness;
import com.Vpetsoft.VpetsoftApp.dto.OutptTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/outptType", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class OutptTypeController {
    @Autowired
    private OutptTypeBusiness outptTypeBusiness; // Mantenemos el nombre de la variable como OutptTypeImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllOutptTypes() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<OutptTypeDto> outptTypeDtoList = this.outptTypeBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",outptTypeDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getOutptTypeById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            OutptTypeDto outptTypeDto = outptTypeBusiness.findOutptTypeById(id);

            if (outptTypeDto != null) {
                response.put("status", "success");
                response.put("data", outptTypeDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Tipo salida no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el tipo salida: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createOutptType(@RequestBody OutptTypeDto outptTypeDto) {
        try {
            // Intentar crear el ingreso utilizando el MedicalExamBusiness
            outptTypeBusiness.createOutptType(outptTypeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el examen medico se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Tipo salida creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el tipo salida: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMedicalFormula(@PathVariable int id, @RequestBody OutptTypeDto outptTypeDto) {
        try {
            // Intentar actualizar el examen medico utilizando MedicalExamBusiness
            outptTypeBusiness.createOutptType(outptTypeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el examen medico se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Tipo salida actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el tipo salida: " + e.getMessage());
        }
    }
}
