package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.RegistrationEntry;
import com.Vpetsoft.VpetsoftApp.repository.RegistrationEntryRepository;
import com.Vpetsoft.VpetsoftApp.service.RegistrationEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrationEntryImp implements RegistrationEntryService {
    @Autowired
    private RegistrationEntryRepository registrationEntryRepository;
    @Override
    public List<RegistrationEntry> findAll() throws Exception {return this.registrationEntryRepository.findAll();}

    @Override
    public RegistrationEntry findById(int id) {
        return this.registrationEntryRepository.findById(id);
    }

    @Override
    public void create(RegistrationEntry registrationEntry) {
        this.registrationEntryRepository.save(registrationEntry);
    }

    @Override
    public void update(RegistrationEntry registrationEntry) {
        this.registrationEntryRepository.save(registrationEntry);
    }

    @Override
    public void delete(RegistrationEntry registrationEntry) {
        this.registrationEntryRepository.delete(registrationEntry);
    }

}
