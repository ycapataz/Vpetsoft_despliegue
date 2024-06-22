package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAll() throws Exception;
    public Customer findById(int id);
    public void create (Customer customer)throws Exception;
    public void update (Customer customer);
    public void delete (Customer customer);
}
