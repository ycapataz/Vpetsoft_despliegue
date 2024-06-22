package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Post;
import com.Vpetsoft.VpetsoftApp.repository.PostRepository;
import com.Vpetsoft.VpetsoftApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImp implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public List<Post> findAll() throws Exception {
        return this.postRepository.findAll();
    }

    @Override
    public Post findById(int id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void create(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void update(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void delete(Post post) {this.postRepository.delete(post);
    }


}
