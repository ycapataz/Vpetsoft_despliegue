package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.IncomeType;

import java.util.List;

public interface IncomeTypeService {
    public List<IncomeType> findAll() throws Exception;
    public IncomeType findById(int id);
    public void create (IncomeType incomeType);
    public void update (IncomeType incomeType);
    public void delete (IncomeType incomeType);

}
