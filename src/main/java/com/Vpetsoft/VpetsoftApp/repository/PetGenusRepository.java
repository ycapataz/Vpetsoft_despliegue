package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetGenusRepository extends JpaRepository<PetGenus, Integer> {
    @Query(value = "select p from PetGenus p where p.id=:id")
    public PetGenus findById(int id);

}
