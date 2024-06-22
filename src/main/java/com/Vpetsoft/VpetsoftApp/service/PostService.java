package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.Post;
import java.util.List;

public interface PostService {

    public List<Post> findAll() throws Exception;
    public Post findById(int id);
    public void create (Post post);
    public void update (Post post);
    public void delete (Post post);


}
