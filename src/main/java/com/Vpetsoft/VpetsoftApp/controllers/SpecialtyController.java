package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.SpecialtyBusiness;
import com.Vpetsoft.VpetsoftApp.dto.SpecialtyDto;
import com.Vpetsoft.VpetsoftApp.entity.Specialty;
import com.Vpetsoft.VpetsoftApp.service.imp.SpecialtyImp; // Importamos la nueva clase.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/Specialty",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class SpecialtyController {
    @Autowired
    SpecialtyBusiness specialtyBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllSpecialty(){
        Map<String,Object> respuesta=new HashMap<>();

        List<SpecialtyDto> specialtyDtoList=this.specialtyBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",specialtyDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getSpecialtyById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            SpecialtyDto specialtyDto = specialtyBusiness.findSpecialtyById(id);

            if (specialtyDto != null) {
                response.put("status", "success");
                response.put("data", specialtyDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Especialidad no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la cita: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createSpecialty(@RequestBody SpecialtyDto specialtyDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            specialtyBusiness.createSpecialty(specialtyDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Especialidad creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la especialidad: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSpecialty(@PathVariable int id, @RequestBody SpecialtyDto specialtyDto) {
        try {
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            specialtyBusiness.updateSpecialty(specialtyDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Especialidad actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la especialidad: " + e.getMessage());
        }
    }
}
