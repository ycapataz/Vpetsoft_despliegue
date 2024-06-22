package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.RegistrationEntry;
import java.util.List;
public interface RegistrationEntryService {

    public List<RegistrationEntry> findAll() throws Exception;
    public RegistrationEntry findById(int id);
    public void create (RegistrationEntry registrationEntry);
    public void update (RegistrationEntry registrationEntry);
    public void delete (RegistrationEntry registrationEntry);
}
