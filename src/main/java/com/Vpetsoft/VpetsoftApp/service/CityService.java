package com.Vpetsoft.VpetsoftApp.service;


import com.Vpetsoft.VpetsoftApp.entity.City;

import java.util.List;

public interface CityService {
    public List<City> findAll() throws Exception;
    public City findById(int id);
    public void create (City city);
    public void update (City city);
    public void delete (City city);
}
