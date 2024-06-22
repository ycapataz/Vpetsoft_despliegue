package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.CategoryBusiness;
import com.Vpetsoft.VpetsoftApp.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryBusiness categoryBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        try {
            List<CategoryDto> categoryDtos = categoryBusiness.findAll();
            return ResponseEntity.ok(categoryDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable int id) {
        try {
            CategoryDto categoryDto = categoryBusiness.findCategoryById(id);
            if (categoryDto != null) {
                return ResponseEntity.ok(categoryDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            String message = categoryBusiness.createCategory(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la categoría: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        try {
            String message = categoryBusiness.updateCategory(categoryDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la categoría: " + e.getMessage());
        }
    }
}