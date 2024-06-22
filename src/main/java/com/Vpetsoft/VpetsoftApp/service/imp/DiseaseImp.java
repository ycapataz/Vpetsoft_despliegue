package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.Disease;
import com.Vpetsoft.VpetsoftApp.repository.DiseaseRepository;
import com.Vpetsoft.VpetsoftApp.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseImp implements DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Override
    public List<Disease> findAll() throws Exception {
        return this.diseaseRepository.findAll();
    }

    @Override
    public Disease findById(int id) {
        return this.diseaseRepository.findById(id);
    }

    @Override
    public void create(Disease disease) {
        this.diseaseRepository.save(disease);
    }

    @Override
    public void update(Disease disease) {
        this.diseaseRepository.save(disease);
    }

    @Override
    public void delete(Disease disease) {
        this.diseaseRepository.delete(disease);
    }
}
