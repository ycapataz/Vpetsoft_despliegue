package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.PetGenusBusiness;
import com.Vpetsoft.VpetsoftApp.dto.PetGenusDto;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;
import com.Vpetsoft.VpetsoftApp.service.imp.PetGenusImp; // Importamos la nueva clase.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/PetGenus",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class PetGenusController {
    @Autowired
    PetGenusBusiness petGenusBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllPetGenus(){
        Map<String,Object> respuesta=new HashMap<>();

        List<PetGenusDto> petGenusDtoList=this.petGenusBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",petGenusDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getPetGenusById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            PetGenusDto petGenusDto = petGenusBusiness.findPetGenusById(id);

            if (petGenusDto != null) {
                response.put("status", "success");
                response.put("data", petGenusDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Genero no encontrada");
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
    public ResponseEntity<String> createPetGenus(@RequestBody PetGenusDto petGenusDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            petGenusBusiness.createPetGenus(petGenusDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Genero creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el genero: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePetGenus(@PathVariable int id, @RequestBody PetGenusDto petGenusDto) {
        try {
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            petGenusBusiness.updatePetGenus(petGenusDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Genero actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el genero: " + e.getMessage());
        }
    }
}

