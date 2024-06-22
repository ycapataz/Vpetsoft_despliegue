package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ProviderRepository extends JpaRepository <Provider, Integer> {
    @Query(value = "select p from Provider p where p.id=:id")
    public Provider findById(int id);
}
