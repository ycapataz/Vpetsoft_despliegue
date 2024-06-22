package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.Species;

import java.util.List;

public interface SpeciesService {
    public List<Species> findAll() throws Exception;
    public Species findById(int id);
    public void create (Species species);
    public void update (Species species);
    public void delete (Species species);
}
