package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.OutptType;
import com.Vpetsoft.VpetsoftApp.repository.OutptTypeRepository;
import com.Vpetsoft.VpetsoftApp.service.OutptTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutptTypeImp implements OutptTypeService {
    @Autowired
    private OutptTypeRepository outptTypeRepository;
    @Override
    public List<OutptType> findAll() throws Exception {
        return this.outptTypeRepository.findAll();
    }

    @Override
    public OutptType findById(int id) {
        return this.outptTypeRepository.findById(id);
    }

    @Override
    public void create(OutptType outptType) {
        this.outptTypeRepository.save(outptType);
    }

    @Override
    public void update(OutptType outptType) {
        this.outptTypeRepository.save(outptType);
    }

    @Override
    public void delete(OutptType outptType) {
        this.outptTypeRepository.delete(outptType);
    }
}
