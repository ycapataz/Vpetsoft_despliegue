package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.CityBusiness;
import com.Vpetsoft.VpetsoftApp.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityBusiness cityBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<CityDto>> findAllCities() {
        try {
            List<CityDto> cityDtos = cityBusiness.findAll();
            return ResponseEntity.ok(cityDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<CityDto> findCityById(@PathVariable int id) {
        try {
            CityDto cityDto = cityBusiness.findCityById(id);
            if (cityDto != null) {
                return ResponseEntity.ok(cityDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCity(@RequestBody CityDto cityDto) {
        try {
            String message = cityBusiness.createCity(cityDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la ciudad: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCity(@PathVariable int id, @RequestBody CityDto cityDto) {
        try {
            String message = cityBusiness.updateCity(cityDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la ciudad: " + e.getMessage());
        }
    }
}
