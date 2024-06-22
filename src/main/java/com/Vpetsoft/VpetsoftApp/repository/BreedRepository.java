package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    @Query(value = "select p from Breed p where p.id=:id")
    public Breed findById(int id);
}
