package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.MedicalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExamRepository extends JpaRepository<MedicalExam, Integer> {
    @Query(value = "select p from MedicalExam p where p.id=:id")
    public MedicalExam findById(int id);

}
