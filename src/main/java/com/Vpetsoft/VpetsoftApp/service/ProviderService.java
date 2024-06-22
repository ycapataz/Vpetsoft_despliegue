package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import java.util.List;
public interface ProviderService {
    public List<Provider> findAll() throws Exception;
    public Provider findById(int id);
    public void create (Provider provider);
    public void update (Provider provider);
    public void delete (Provider provider);
}
