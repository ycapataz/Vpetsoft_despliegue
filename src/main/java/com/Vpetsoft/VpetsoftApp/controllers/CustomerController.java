package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.CustomerBusiness;
import com.Vpetsoft.VpetsoftApp.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/Customers",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerBusiness customerBusiness;

    // Método para obtener todas los propietarios
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllCustomers() {
        Map<String, Object> respuesta = new HashMap<>();

        List<CustomerDto> customerDtoList = this.customerBusiness.findAll();

        respuesta.put("STATUS", "SUCCESS");
        respuesta.put("DATA", customerDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("Get/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            CustomerDto customerDto = customerBusiness.findCustomerById(id);
            if (customerDto != null) {
                response.put("status", "success");
                response.put("data", customerDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Propietario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el propietario: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            // Intentar crear el propietario utilizando el CustomerBusiness
            customerBusiness.createCustomer(customerDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el propietario se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Propietario creado exitosamente");
        } catch (RuntimeException e) {
            // Capturar específicamente la excepción de DNI duplicado
            if (e.getCause() instanceof DataIntegrityViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El DNI ya está registrado");
            } else {
                // Sí ocurre algún otro error durante el proceso, devolver un ResponseEntity
                // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el propietario: " + e.getMessage());
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto) {
        try {
            customerDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            customerBusiness.updateCustomer(customerDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Propietario actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el propietario: " + e.getMessage());
   }
}
}