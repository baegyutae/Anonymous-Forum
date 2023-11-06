package com.sparta.Anonymous.Forum.service;

import com.sparta.Anonymous.Forum.domain.Post;
import com.sparta.Anonymous.Forum.dto.PostDto;
import com.sparta.Anonymous.Forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        post.setCreationTime(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found!"));
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreationTimeDesc();
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Override
    public Post updatePost(Long id, PostDto postDto) {
        Post post = getPost(id);
        if (post.getPassword().equals(postDto.getPassword())) {
            post.setTitle(postDto.getTitle());
            post.setAuthor(postDto.getAuthor());
            post.setContent(postDto.getContent());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Incorrect password!");
        }
    }

    @Override
    public boolean deletePost(Long id, String password) {
        Post post = getPost(id);
        if (post.getPassword().equals(password)) {
            postRepository.delete(post);
            return true;
        } else {
            return false;
        }
    }
}
