package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll() throws Exception;
    public Category findById(int id);
    public void create (Category category);
    public void update (Category category);
    public void delete (Category category);
}
