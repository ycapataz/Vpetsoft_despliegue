package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.EpsBusiness;
import com.Vpetsoft.VpetsoftApp.dto.EpsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/eps", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class EpsController {
    @Autowired
    EpsBusiness epsBusiness; // Mantenemos el nombre de la variable como EpsImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllEps() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<EpsDto> epsDtoList = this.epsBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",epsDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getEpsById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EpsDto epsDto = epsBusiness.findEpsById(id);

            if (epsDto != null) {
                response.put("status", "success");
                response.put("data", epsDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Eps no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la eps: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createEps(@RequestBody EpsDto epsDto) {
        try {
            // Intentar crear la eps utilizando el EpsBusiness
            epsBusiness.createEps(epsDto);

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
    public ResponseEntity<String> updateEps(@PathVariable int id, @RequestBody EpsDto epsDto) {
        try {
            // Intentar actualizar la eps utilizando el EpsBusiness
            epsBusiness.updateEps(epsDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Eps actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado ingreso: " + e.getMessage());
        }
    }
}
