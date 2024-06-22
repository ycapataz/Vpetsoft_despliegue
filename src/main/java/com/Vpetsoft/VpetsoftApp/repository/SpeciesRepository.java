package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    @Query(value = "select p from Species p where p.id=:id")
    public Species findById(int id);
}
