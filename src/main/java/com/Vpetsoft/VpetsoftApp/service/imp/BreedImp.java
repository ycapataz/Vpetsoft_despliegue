package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.Breed;
import com.Vpetsoft.VpetsoftApp.repository.BreedRepository;
import com.Vpetsoft.VpetsoftApp.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedImp implements BreedService {

    @Autowired
    private BreedRepository breedRepository;
    @Override
    public List<Breed> findAll() throws Exception {
        return this.breedRepository.findAll();
    }

    @Override
    public Breed findById(int id) {
        return this.breedRepository.findById(id);
    }

    @Override
    public void create(Breed breed) {
        this.breedRepository.save(breed);
    }

    @Override
    public void update(Breed breed) {
        this.breedRepository.save(breed);
    }

    @Override
    public void delete(Breed breed) {
        this.breedRepository.delete(breed);
    }
}
