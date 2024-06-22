package com.Vpetsoft.VpetsoftApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.Vpetsoft.VpetsoftApp.entity.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query(value = "select p from Pet p where p.id=:id")
    public Pet findById(int id);
}
