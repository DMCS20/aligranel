package com.group2.aligranel.comment.service;

import com.group2.aligranel.comment.dto.CommentRequestDTO;
import com.group2.aligranel.comment.dto.CommentResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommentService {
    List<CommentResponseDTO> getCommentsByPostId(ObjectId postId);
    CommentResponseDTO createComment(CommentRequestDTO comment, ObjectId postId);
    void updateComment(CommentRequestDTO comment, ObjectId id);
    void deleteComment(ObjectId id);
}
