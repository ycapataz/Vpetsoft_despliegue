package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.IncomeTypeBusiness;
import com.Vpetsoft.VpetsoftApp.dto.IncomeTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/incomeType", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class IncomeTypeController {
    @Autowired
    private IncomeTypeBusiness incomeTypeBusiness; // Mantenemos el nombre de la variable como IncomeTypeImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllIncomeTypes() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<IncomeTypeDto> incomeTypeDtoList = this.incomeTypeBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",incomeTypeDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getIncomeTypeById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            IncomeTypeDto incomeTypeDto = incomeTypeBusiness.findIncomeTypeById(id);

            if (incomeTypeDto != null) {
                response.put("status", "success");
                response.put("data", incomeTypeDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Tipo ingreso no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el tipo ingreso: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createIncomeType(@RequestBody IncomeTypeDto incomeTypeDto) {
        try {
            // Intentar crear el ingreso utilizando el IncomeBusiness
            incomeTypeBusiness.createIncomeType(incomeTypeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el tipo ingreso se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Tipo ingreso creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el tipo ingreso: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIncomeType(@PathVariable int id, @RequestBody IncomeTypeDto incomeTypeDto) {
        try {
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            incomeTypeBusiness.updateIncomeType(incomeTypeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el ingreso se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Tipo ingreso actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el tipo ingreso: " + e.getMessage());
        }
    }
}
