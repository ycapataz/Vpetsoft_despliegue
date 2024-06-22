package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.CheckOutBusiness;
import com.Vpetsoft.VpetsoftApp.dto.CheckOutDto;
import com.Vpetsoft.VpetsoftApp.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checkouts")
@CrossOrigin("*")
public class CheckOutController {

    @Autowired
    CheckOutBusiness checkOutBusiness;
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllCheckout() {
        Map<String, Object> respuesta = new HashMap<>();

        List<CheckOutDto> checkOutDtoList = this.checkOutBusiness.findAll();

        respuesta.put("STATUS", "SUCCES");
        respuesta.put("DATA", checkOutDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getCheckOutById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            CheckOutDto checkOutDto = checkOutBusiness.findCheckOutById(id);

            if (checkOutDto != null) {
                response.put("status", "success");
                response.put("data", checkOutDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Salida no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la entrada: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CheckOutDto checkOutDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            checkOutBusiness.createCheckOut(checkOutDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Salida creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la salida: " + e.getMessage());
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody CheckOutDto checkOutDto) {
        try {
            checkOutDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            checkOutBusiness.updateCheckOut(checkOutDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Salida actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la salida: " + e.getMessage());
        }
    }
}
