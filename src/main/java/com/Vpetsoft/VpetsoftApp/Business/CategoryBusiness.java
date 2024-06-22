package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.AppointmentDto;
import com.Vpetsoft.VpetsoftApp.dto.BreedDto;
import com.Vpetsoft.VpetsoftApp.dto.CategoryDto;
import com.Vpetsoft.VpetsoftApp.entity.Breed;
import com.Vpetsoft.VpetsoftApp.entity.Category;
import com.Vpetsoft.VpetsoftApp.service.BreedService;
import com.Vpetsoft.VpetsoftApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Categoria
@Component
public class CategoryBusiness {
    @Autowired
    private CategoryService categoryService;
    private List<Category> CategoryList;

    public List<CategoryDto> findAll() {
        List<CategoryDto> categoryDtoList =new ArrayList<>();
        try {
            this.CategoryList = this.categoryService.findAll();
            this.CategoryList.forEach(category -> {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());

                categoryDtoList.add(categoryDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar las categorias", e);
        }
        return categoryDtoList;
    }
    public CategoryDto findCategoryById(int id) {
        try {
            Category category = this.categoryService.findById(id);

            if (category != null) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                return categoryDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la categoría por ID", e);
        }
    }

    public String createCategory(CategoryDto categoryDto) {
        try {
            Category category = new Category();
            category.setName(categoryDto.getName());

            this.categoryService.create(category);

            return "Categoría creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la categoría", e);
        }
    }

    public String updateCategory(CategoryDto categoryDto) {
        try {
            Category category = categoryService.findById(categoryDto.getId());

            if (category != null) {
                category.setName(categoryDto.getName());

                categoryService.update(category);

                return "Categoría actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la categoría. La categoría no existe con ID: " + categoryDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la categoría", e);
        }
    }
}
