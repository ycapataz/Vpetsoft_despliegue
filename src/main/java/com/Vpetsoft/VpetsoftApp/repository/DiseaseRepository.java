package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    @Query(value = "select p from Disease p where p.id=:id")
    public Disease findById(int id);
}
