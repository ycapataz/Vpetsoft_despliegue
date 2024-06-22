package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.SpeciesBusiness;
import com.Vpetsoft.VpetsoftApp.dto.SpeciesDto;
import com.Vpetsoft.VpetsoftApp.entity.Species;
import com.Vpetsoft.VpetsoftApp.service.imp.SpeciesImp; // Importamos la nueva clase.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/Species",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class SpeciesController {
    @Autowired
    SpeciesBusiness speciesBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllSpecies(){
        Map<String,Object> respuesta=new HashMap<>();

        List<SpeciesDto> speciesDtoList=this.speciesBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",speciesDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getSpeciesById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            SpeciesDto speciesDto = speciesBusiness.findSpeciesById(id);

            if (speciesDto != null) {
                response.put("status", "success");
                response.put("data", speciesDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Especie no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la especie: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createSpecies(@RequestBody SpeciesDto speciesDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            speciesBusiness.createSpecies(speciesDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Especie creada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la especie: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSpecies(@PathVariable int id, @RequestBody SpeciesDto speciesDto) {
        try {
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            speciesBusiness.updateSpecies(speciesDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Especie actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la especie: " + e.getMessage());
        }
    }
}

