package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;

import java.util.List;

public interface PetGenusService {

    public List<PetGenus> findAll() throws Exception;
    public PetGenus findById(int id);
    public void create (PetGenus petGenus);
    public void update (PetGenus petGenus);
    public void delete (PetGenus petGenus);
}
