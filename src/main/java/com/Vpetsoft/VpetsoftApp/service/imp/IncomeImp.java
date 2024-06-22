package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Income;
import com.Vpetsoft.VpetsoftApp.repository.IncomeRepository;
import com.Vpetsoft.VpetsoftApp.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomeImp implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    @Override
    public List<Income> findAll() throws Exception {
        return this.incomeRepository.findAll();
    }

    @Override
    public Income findById(int id) {
        return this.incomeRepository.findById(id);
    }

    @Override
    public void create(Income income) {
        this.incomeRepository.save(income);
    }

    @Override
    public void update(Income income) {
        this.incomeRepository.save(income);
    }

    @Override
    public void delete(Income income) {
        this.incomeRepository.delete(income);
    }
}
