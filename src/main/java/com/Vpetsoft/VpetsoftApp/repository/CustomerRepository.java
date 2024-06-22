package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select p from Customer p where p.id=:id")
    public Customer findById(int id);
    boolean existsByDni(String dni);
}
