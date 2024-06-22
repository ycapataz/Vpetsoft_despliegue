package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;
import com.Vpetsoft.VpetsoftApp.repository.PetGenusRepository;
import com.Vpetsoft.VpetsoftApp.service.PetGenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetGenusImp implements PetGenusService {

    @Autowired
    private PetGenusRepository petGenusRepository;
    @Override
    public List<PetGenus> findAll() throws Exception {
        return this.petGenusRepository.findAll();
    }

    @Override
    public PetGenus findById(int id) {
        return this.petGenusRepository.findById(id);
    }

    @Override
    public void create(PetGenus petGenus) {
        this.petGenusRepository.save(petGenus);
    }

    @Override
    public void update(PetGenus petGenus) {
        this.petGenusRepository.save(petGenus);
    }

    @Override
    public void delete(PetGenus petGenus) {
        this.petGenusRepository.delete(petGenus);
    }


}
