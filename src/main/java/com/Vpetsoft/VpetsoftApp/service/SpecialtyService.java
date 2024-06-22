package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.Specialty;
import java.util.List;
public interface SpecialtyService {

    public List<Specialty> findAll() throws Exception;
    public Specialty findById(int id);
    public void create (Specialty specialty);
    public void update (Specialty specialty);
    public void delete (Specialty specialty);
}
