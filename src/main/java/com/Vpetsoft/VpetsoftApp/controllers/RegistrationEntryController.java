package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.RegistrationEntryBusiness;
import com.Vpetsoft.VpetsoftApp.dto.RegistrationEntryDto;
import com.Vpetsoft.VpetsoftApp.entity.RegistrationEntry;
import com.Vpetsoft.VpetsoftApp.service.imp.RegistrationEntryImp; // Importamos la nueva clase.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/RegistrationEntry",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class RegistrationEntryController {
    @Autowired
    RegistrationEntryBusiness registrationEntryBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllRegistrationEntry(){
        Map<String,Object> respuesta=new HashMap<>();

        List<RegistrationEntryDto> registrationEntryDtoList=this.registrationEntryBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",registrationEntryDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getRegistrationEntryById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            RegistrationEntryDto registrationEntryDto = registrationEntryBusiness.findRegistrationEntryById(id);

            if (registrationEntryDto != null) {
                response.put("status", "success");
                response.put("data", registrationEntryDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Registro de entrada no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el registro de entrada: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createRegistrationEntry(@RequestBody RegistrationEntryDto registrationEntryDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            registrationEntryBusiness.createRegistrationEntry(registrationEntryDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("registro de entrada creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la registro de entrada: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRegistrationEntry(@PathVariable int id, @RequestBody RegistrationEntryDto registrationEntryDto) {
        try {
            registrationEntryDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            registrationEntryBusiness.updateRegistrationEntry(registrationEntryDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("registro de entrada actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el registro de entrada: " + e.getMessage());
        }
    }
}

