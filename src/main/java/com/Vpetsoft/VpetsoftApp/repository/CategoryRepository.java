package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select p from Category p where p.id=:id")
    public Category findById(int id);
}
