package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.Exception.CustomException;
import com.Vpetsoft.VpetsoftApp.entity.Customer;
import com.Vpetsoft.VpetsoftApp.repository.CustomerRepository;
import com.Vpetsoft.VpetsoftApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() throws Exception {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public void create(Customer customer) throws CustomException {
        if (customerRepository.existsByDni(customer.getDni())) {
            throw new CustomException("El DNI ya está registrado.");
        }
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
