package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.City;
import com.Vpetsoft.VpetsoftApp.repository.CityRepository;
import com.Vpetsoft.VpetsoftApp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityImp implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<City> findAll() throws Exception {
        return this.cityRepository.findAll();
    }

    @Override
    public City findById(int id) {
        return this.cityRepository.findById(id);
    }

    @Override
    public void create(City city) {
        this.cityRepository.save(city);
    }

    @Override
    public void update(City city) {
        this.cityRepository.save(city);
    }

    @Override
    public void delete(City city) {
        this.cityRepository.delete(city);
    }
}
