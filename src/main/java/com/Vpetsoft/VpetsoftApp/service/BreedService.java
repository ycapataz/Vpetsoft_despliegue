package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Breed;

import java.util.List;

public interface BreedService {
    public List<Breed> findAll() throws Exception;
    public Breed findById(int id);
    public void create (Breed breed);
    public void update (Breed breed);
    public void delete (Breed breed);
}
