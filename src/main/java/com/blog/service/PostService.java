package com.blog.service;

import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id) throws ResourceNotFoundException;


    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String orderBy);

    PostDto updatePost(long postId, PostDto postDto);
}
