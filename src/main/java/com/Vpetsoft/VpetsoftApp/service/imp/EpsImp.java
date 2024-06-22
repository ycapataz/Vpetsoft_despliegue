package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.repository.EpsRepository;
import com.Vpetsoft.VpetsoftApp.service.EpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EpsImp implements EpsService {

    @Autowired
    private EpsRepository epsRepository;
    @Override
    public List<Eps> findAll() throws Exception {return this.epsRepository.findAll();}

    @Override
    public Eps findById(int id) {
        return this.epsRepository.findById(id);
    }

    @Override
    public void create(Eps eps) {
        this.epsRepository.save(eps);
    }

    @Override
    public void update(Eps eps) {
        this.epsRepository.save(eps);
    }

    @Override
    public void delete(Eps eps) {
        this.epsRepository.delete(eps);
    }
}
