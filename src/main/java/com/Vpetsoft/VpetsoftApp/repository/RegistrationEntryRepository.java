package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.RegistrationEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationEntryRepository extends JpaRepository<RegistrationEntry, Integer> {
    @Query(value = "select p from RegistrationEntry p where p.id=:id")
    public RegistrationEntry findById(int id);
}
