package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    @Query(value = "select p from State p where p.id=:id")
    public State findById(int id);
}
