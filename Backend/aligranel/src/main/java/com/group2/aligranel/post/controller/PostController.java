package com.group2.aligranel.post.controller;

import com.group2.aligranel.post.dto.request.PostRequestDTO;
import com.group2.aligranel.post.dto.response.PostResponseDTO;
import com.group2.aligranel.post.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @Transactional(readOnly = true)
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable ObjectId id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUserId(@PathVariable ObjectId userId){
        return new ResponseEntity<>(postService.getPostsByUserId(userId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequest, @PathVariable ObjectId userId){
        return new ResponseEntity<>(postService.createPost(postRequest, userId), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/posts/{id}")
    public ResponseEntity updatePost(@RequestBody PostRequestDTO postRequest, @PathVariable ObjectId id){
        postService.updatePost(postRequest, id);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletePost(@PathVariable ObjectId id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post with ID" + id + " has been deleted successfully", HttpStatus.OK);
    }
}
