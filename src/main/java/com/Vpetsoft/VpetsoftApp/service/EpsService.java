package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.Eps;
import java.util.List;
public interface EpsService {
    public List<Eps> findAll() throws Exception;
    public Eps findById(int id);
    public void create (Eps eps);
    public void update (Eps eps);
    public void delete (Eps eps);
}
