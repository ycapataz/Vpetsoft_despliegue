package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.PetBusiness;
import com.Vpetsoft.VpetsoftApp.dto.PetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/pet", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class PetController {
    @Autowired
    private PetBusiness petBusiness; // Mantenemos el nombre de la variable como PetImp.

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllPets() throws  Exception{
        Map<String,Object> respuesta = new HashMap<>();
        List<PetDto> petDtoList = this.petBusiness.findAll();
        respuesta.put("status","success");
        respuesta.put("data",petDtoList);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getPetById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            PetDto petDto = petBusiness.findPetById(id);

            if (petDto != null) {
                response.put("status", "success");
                response.put("data", petDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Mascota no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la mascota: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createPet(@RequestBody PetDto petDto) {
        try {
            // Intentar crear el ingreso utilizando el IncomeBusiness
            petBusiness.createPet(petDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que el ingreso se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Mascota creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la mascota: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePet(@PathVariable int id, @RequestBody PetDto petDto) {
        try {
            petDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            petBusiness.updatePet(petDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que el ingreso se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Mascota actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la mascota: " + e.getMessage());
        }
    }
}
