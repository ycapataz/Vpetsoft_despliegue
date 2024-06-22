package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.BreedBusiness;
import com.Vpetsoft.VpetsoftApp.dto.BreedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/breeds")
@CrossOrigin("*")
public class BreedController {

    @Autowired
    private BreedBusiness breedBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<BreedDto>> findAllBreeds() {
        try {
            List<BreedDto> breedDtos = breedBusiness.findAll();
            return ResponseEntity.ok(breedDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<BreedDto> findBreedById(@PathVariable int id) {
        try {
            BreedDto breedDto = breedBusiness.findBreedById(id);
            if (breedDto != null) {
                return ResponseEntity.ok(breedDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBreed(@RequestBody BreedDto breedDto) {
        try {
            String message = breedBusiness.createBreed(breedDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la raza: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBreed(@PathVariable int id, @RequestBody BreedDto breedDto) {
        try {
            String message = breedBusiness.updateBreed(breedDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la raza: " + e.getMessage());
        }
    }
}
