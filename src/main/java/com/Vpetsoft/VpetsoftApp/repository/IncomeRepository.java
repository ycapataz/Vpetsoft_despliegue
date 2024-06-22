package com.Vpetsoft.VpetsoftApp.repository;


import com.Vpetsoft.VpetsoftApp.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer>{
    @Query(value = "select p from Income p where p.id=:id")
    public Income findById(int id);
}
