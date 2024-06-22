package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.OutptType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OutptTypeRepository extends JpaRepository<OutptType, Integer> {
    @Query(value = "select p from OutptType p where p.id=:id")
    public OutptType findById(int id);
}
