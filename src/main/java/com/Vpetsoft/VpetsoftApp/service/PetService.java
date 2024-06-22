package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.Pet;

import java.util.List;

public interface PetService {
    public List<Pet> findAll();
    public Pet findById(int id);
    public void create (Pet pet);
    public void update (Pet pet);
    public void delete (Pet pet);

}
