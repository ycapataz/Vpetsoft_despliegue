package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Income;

import java.util.List;

public interface IncomeService {

    public List<Income> findAll() throws Exception;
    public Income findById(int id);
    public void create (Income income);
    public void update (Income income);
    public void delete (Income income);
}
