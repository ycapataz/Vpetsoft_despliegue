package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Employee;

import java.util.List;


public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int id);
    public void create (Employee employee);
    public void update (Employee employee);
    public void delete (Employee employee);

    Employee authenticate(String mail, String password);
}
