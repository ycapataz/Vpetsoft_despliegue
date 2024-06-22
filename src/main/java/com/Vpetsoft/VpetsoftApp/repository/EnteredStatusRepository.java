package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EnteredStatusRepository extends JpaRepository<EnteredStatus, Integer> {
    @Query(value = "select p from EnteredStatus p where p.id=:id")
    public EnteredStatus findById(int id);
}
