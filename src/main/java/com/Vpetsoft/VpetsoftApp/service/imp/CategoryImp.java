package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.Category;
import com.Vpetsoft.VpetsoftApp.repository.CategoryRepository;
import com.Vpetsoft.VpetsoftApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() throws Exception {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public void create(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        this.categoryRepository.delete(category);
    }
}
