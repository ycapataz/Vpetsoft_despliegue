package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.IncomeType;
import com.Vpetsoft.VpetsoftApp.repository.IncomeTypeRepository;
import com.Vpetsoft.VpetsoftApp.service.IncomeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomeTypeImp implements IncomeTypeService {

    @Autowired
    private IncomeTypeRepository incomeTypeRepository;
    @Override
    public List<IncomeType> findAll() throws Exception {
        return this.incomeTypeRepository.findAll();
    }

    @Override
    public IncomeType findById(int id) {
        return this.incomeTypeRepository.findById(id);
    }

    @Override
    public void create(IncomeType incomeType) {
        this.incomeTypeRepository.save(incomeType);
    }

    @Override
    public void update(IncomeType incomeType) {
        this.incomeTypeRepository.save(incomeType);
    }

    @Override
    public void delete(IncomeType incomeType) {
        this.incomeTypeRepository.delete(incomeType);
    }
}
