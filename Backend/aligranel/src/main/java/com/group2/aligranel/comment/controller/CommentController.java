package com.group2.aligranel.comment.controller;

import com.group2.aligranel.comment.dto.CommentRequestDTO;
import com.group2.aligranel.comment.dto.CommentResponseDTO;
import com.group2.aligranel.comment.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Transactional(readOnly = true)
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPostId(@PathVariable ObjectId postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequest, @PathVariable ObjectId postId){
        return new ResponseEntity<>(commentService.createComment(commentRequest, postId), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/comments/{id}")
    public ResponseEntity updateComment(@RequestBody CommentRequestDTO commentRequest, @PathVariable ObjectId id){
        commentService.updateComment(commentRequest, id);
        return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable ObjectId id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment with ID" + id + " has been deleted successfully", HttpStatus.OK);
    }
}
