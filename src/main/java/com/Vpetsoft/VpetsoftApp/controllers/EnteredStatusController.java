package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.EnteredStatusBusiness;
import com.Vpetsoft.VpetsoftApp.dto.EnteredStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/enteredStatus", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class EnteredStatusController {
    @Autowired
    EnteredStatusBusiness enteredStatusBusiness; // Mantenemos el nombre de la variable como EnteredStatusImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllEnteredStatus() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<EnteredStatusDto> enteredStatusDtoList = this.enteredStatusBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",enteredStatusDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getEnteredStatusById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EnteredStatusDto enteredStatusDto = enteredStatusBusiness.findEnteredStatusById(id);

            if (enteredStatusDto != null) {
                response.put("status", "success");
                response.put("data", enteredStatusDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Estado ingreso no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el estado ingreso: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createEnteredStatus(@RequestBody EnteredStatusDto enteredStatusDto) {
        try {
            // Intentar crear el estado ingreso utilizando el EnteredStatusBusiness
            enteredStatusBusiness.createEnteredStatus(enteredStatusDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Estado ingreso creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el estado ingreso: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEnteredStatus(@PathVariable int id, @RequestBody EnteredStatusDto enteredStatusDto) {
        try {
            // Intentar actualizar el estado ingreso utilizando el EnteredStatusBusiness
            enteredStatusBusiness.updateEnteredStatus(enteredStatusDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Estado ingreso actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado ingreso: " + e.getMessage());
        }
    }
}
