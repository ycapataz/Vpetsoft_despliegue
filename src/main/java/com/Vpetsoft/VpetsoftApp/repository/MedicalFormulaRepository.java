package com.Vpetsoft.VpetsoftApp.repository;


import com.Vpetsoft.VpetsoftApp.entity.MedicalFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFormulaRepository extends JpaRepository <MedicalFormula, Integer> {
    @Query(value = "select p from MedicalFormula p where p.id=:id")
    public MedicalFormula findById(int id);
}
