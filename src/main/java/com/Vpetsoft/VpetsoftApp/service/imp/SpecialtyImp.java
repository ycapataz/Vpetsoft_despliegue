package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Specialty;
import com.Vpetsoft.VpetsoftApp.repository.SpecialtyRepository;
import com.Vpetsoft.VpetsoftApp.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SpecialtyImp implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Override
    public List<Specialty> findAll() throws Exception {return this.specialtyRepository.findAll();}

    @Override
    public Specialty findById(int id) {
        return this.specialtyRepository.findById(id);
    }

    @Override
    public void create(Specialty specialty) {
        this.specialtyRepository.save(specialty);
    }

    @Override
    public void update(Specialty specialty) {
        this.specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        this.specialtyRepository.delete(specialty);
    }
}
