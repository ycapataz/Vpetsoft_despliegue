package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Species;
import com.Vpetsoft.VpetsoftApp.repository.SpeciesRepository;
import com.Vpetsoft.VpetsoftApp.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpeciesImp implements SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepository;
    @Override
    public List<Species> findAll() throws Exception {return this.speciesRepository.findAll();}

    @Override
    public Species findById(int id) {
        return this.speciesRepository.findById(id);
    }

    @Override
    public void create(Species species) {
        this.speciesRepository.save(species);
    }

    @Override
    public void update(Species species) {
        this.speciesRepository.save(species);
    }

    @Override
    public void delete(Species species) {this.speciesRepository.delete(species);}
}
