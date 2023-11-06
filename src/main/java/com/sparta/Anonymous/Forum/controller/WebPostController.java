package com.sparta.Anonymous.Forum.controller;

import ch.qos.logback.core.model.Model;
import com.sparta.Anonymous.Forum.dto.PostDto;
import com.sparta.Anonymous.Forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class WebPostController {

    private final PostService postService;

    @Autowired
    public WebPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<PostDto> postDtos = postService.getAllPosts();
        model.addText("posts");
        return "index"; // Thymeleaf 템플릿 파일의 이름
    }
}
