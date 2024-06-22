package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import com.Vpetsoft.VpetsoftApp.repository.ProviderRepository;
import com.Vpetsoft.VpetsoftApp.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProviderImp implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    @Override
    public List<Provider> findAll() throws Exception {return this.providerRepository.findAll();}

    @Override
    public Provider findById(int id) {
        return this.providerRepository.findById(id);
    }

    @Override
    public void create(Provider provider) {
        this.providerRepository.save(provider);
    }

    @Override
    public void update(Provider provider) {
        this.providerRepository.save(provider);
    }

    @Override
    public void delete(Provider provider) {
        this.providerRepository.delete(provider);
    }


}
