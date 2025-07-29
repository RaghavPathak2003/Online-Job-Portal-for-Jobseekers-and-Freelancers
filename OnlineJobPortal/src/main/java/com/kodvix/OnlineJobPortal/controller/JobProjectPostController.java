package com.kodvix.OnlineJobPortal.controller;

import com.kodvix.OnlineJobPortal.dto.JobProjectPostRequestDto;
import com.kodvix.OnlineJobPortal.dto.JobProjectPostResponseDto;
import com.kodvix.OnlineJobPortal.service.JobProjectPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobProjectPost")
public class JobProjectPostController {

    @Autowired
    private JobProjectPostService postService;

    @PostMapping("/save")
    public ResponseEntity<JobProjectPostResponseDto> createPost(@Valid @RequestBody JobProjectPostRequestDto dto) {
        JobProjectPostResponseDto post = postService.createPost(dto);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobProjectPostResponseDto> getPostById(@PathVariable Long id) {
        JobProjectPostResponseDto get = postService.getPostById(id);
        return ResponseEntity.ok(get);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobProjectPostResponseDto>> getAllPosts() {
        List<JobProjectPostResponseDto> getall = postService.getAllPosts();
        return ResponseEntity.ok(getall);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<JobProjectPostResponseDto> updatePost(@PathVariable Long id,@Valid @RequestBody JobProjectPostRequestDto dto) {
        JobProjectPostResponseDto update = postService.updatePost(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

}
