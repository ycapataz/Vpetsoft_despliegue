package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.IncomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTypeRepository extends JpaRepository<IncomeType, Integer> {
    @Query(value = "select p from IncomeType p where p.id=:id")
    public IncomeType findById(int id);
}
