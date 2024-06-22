package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.AppointmentBusiness;
import com.Vpetsoft.VpetsoftApp.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/Appointment",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class AppointmentController {
    @Autowired
    AppointmentBusiness appointmentBusiness;


//////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllAppointment(){
        Map<String,Object> respuesta=new HashMap<>();

        List<AppointmentDto> appointmentDtoList=this.appointmentBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",appointmentDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
//////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getAppointmentById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            AppointmentDto appointmentDto = appointmentBusiness.findAppointmentById(id);

            if (appointmentDto != null) {
                response.put("status", "success");
                response.put("data", appointmentDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Cita no encontrada");
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
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            appointmentBusiness.createAppointment(appointmentDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Cita creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cita: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable int id, @RequestBody AppointmentDto appointmentDto) {
        try {
            appointmentDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            appointmentBusiness.updateAppointment(appointmentDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Cita actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la cita: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        try {
            // Intentar eliminar la cita utilizando el AppointmentBusiness
            appointmentBusiness.deleteAppointment(id);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha eliminado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Cita eliminada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la cita: " + e.getMessage());
        }
    }
}