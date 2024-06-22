package com.Vpetsoft.VpetsoftApp.repository;
import com.Vpetsoft.VpetsoftApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select p from Post p where p.id=:id")
    public Post findById(int id);
}
