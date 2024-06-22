package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Disease;

import java.util.List;

public interface DiseaseService {
    public List<Disease> findAll() throws Exception;
    public Disease findById(int id);
    public void create (Disease disease);
    public void update (Disease disease);
    public void delete (Disease disease);
}
