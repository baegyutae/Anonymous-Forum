package com.sparta.Anonymous.Forum.service;

import com.sparta.Anonymous.Forum.domain.Post;
import com.sparta.Anonymous.Forum.dto.PostDto;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    Post getPost(Long id);
    List<PostDto> getAllPosts();
    Post updatePost(Long id, PostDto postDto);
    boolean deletePost(Long id, String password);
}
