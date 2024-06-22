package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.StateBusiness;
import com.Vpetsoft.VpetsoftApp.dto.StateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/State",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class StateController {
    @Autowired
    StateBusiness stateBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllState(){
        Map<String,Object> respuesta=new HashMap<>();

        List<StateDto> stateDtoList=this.stateBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",stateDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getStateById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            StateDto stateDto = stateBusiness.findStateById(id);

            if (stateDto != null) {
                response.put("status", "success");
                response.put("data", stateDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Estado no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el estado: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createState(@RequestBody StateDto stateDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            stateBusiness.createState(stateDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Estado creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el estado: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable int id, @RequestBody StateDto stateDto) {
        try {
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            stateBusiness.updateState(stateDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Estado actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado: " + e.getMessage());
        }
    }


}
