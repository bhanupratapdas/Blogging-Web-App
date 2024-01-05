package com.blog.controller;


import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.service.PostService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is Deleted!",HttpStatus.OK);
    }

//    http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&orderBy=asc
    @GetMapping
    public ResponseEntity<List<PostDto>> showPosts(
            @RequestParam(name = "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy
    ){
        List<PostDto> postDtos =  postService.getAllPosts(pageNo,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

//    http://localhost:8080/api/posts?postId

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto> updatePost(
            @RequestParam("postId") long postId,
            @RequestBody PostDto postDto
    ){
        PostDto dtos = postService.updatePost(postId, postDto);

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
}
