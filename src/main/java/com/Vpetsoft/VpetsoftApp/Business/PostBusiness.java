package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.PostDto;
import com.Vpetsoft.VpetsoftApp.entity.Post;
import com.Vpetsoft.VpetsoftApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostBusiness {
    @Autowired
    private PostService postService;
    private List<Post> PostList;

    //Consultar todos
    public List<PostDto>findAll(){
        List<PostDto> postDtoList =new ArrayList<>();
        try{
            this.PostList=this.postService.findAll();
            this.PostList.forEach(post -> {
                PostDto postDto=new PostDto();
                postDto.setId(post.getId());
                postDto.setName(post.getName());
                postDtoList.add(postDto);

            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar cargo", e);
        }
        return postDtoList;
    }
    public PostDto findPostById(int id){
        try{
            Post post=this.postService.findById(id);
            if(post!=null){
                PostDto postDto=new PostDto();
                postDto.setId(post.getId());
                postDto.setName(post.getName());
                return  postDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar cargo por ID ", e);
        }
    }
    public String createPost(PostDto postDto){
        try{
            Post post=new Post();
            post.setName(postDto.getName());

            this.postService.create(post);
            return "Cargo creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el cargo", e);
        }
    }

    public String updatePost(PostDto postDto){
        try{

            Post post=postService.findById(postDto.getId());

            if(post !=null) {
                post.setName(postDto.getName());

                postService.update(post);

                return "Cargo actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el cargo. El registro no existe con ID: " + postDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear cargo", e);
        }
    }
}
