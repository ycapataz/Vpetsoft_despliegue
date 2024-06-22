package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.IncomeBusiness;
import com.Vpetsoft.VpetsoftApp.dto.IncomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/income", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class IncomeController {
    @Autowired
    private IncomeBusiness incomeBusiness; // Mantenemos el nombre de la variable como IncomeImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllIncomes() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<IncomeDto> incomeDtoList = this.incomeBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",incomeDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getIncomeById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            IncomeDto incomeDto = incomeBusiness.findIncomeById(id);

            if (incomeDto != null) {
                response.put("status", "success");
                response.put("data", incomeDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Ingreso no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar el ingreso: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createIncome(@RequestBody IncomeDto incomeDto) {
        try {
            // Intentar crear el ingreso utilizando el IncomeBusiness
            incomeBusiness.createIncome(incomeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el ingreso se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Ingreso creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el ingreso: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIncome(@PathVariable int id, @RequestBody IncomeDto incomeDto) {
        try {
            incomeDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            incomeBusiness.updateIncome(incomeDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el ingreso se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Ingreso actualizado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el ingreso: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable int id) {
        try {
            // Intentar eliminar el ingreso utilizando el IncomeBusiness
            incomeBusiness.deleteIncome(id);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el ingreso se ha eliminado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("ingreso eliminado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el ingreso: " + e.getMessage());
        }
    }
}
