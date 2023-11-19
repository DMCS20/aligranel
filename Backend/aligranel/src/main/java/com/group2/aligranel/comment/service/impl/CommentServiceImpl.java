package com.group2.aligranel.comment.service.impl;

import com.group2.aligranel.comment.dto.CommentRequestDTO;
import com.group2.aligranel.comment.dto.CommentResponseDTO;
import com.group2.aligranel.comment.mapper.CommentMapper;
import com.group2.aligranel.comment.model.Comment;
import com.group2.aligranel.comment.repository.CommentRepository;
import com.group2.aligranel.comment.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentResponseDTO> getCommentsByPostId(ObjectId postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return commentMapper.toCommentResponseDTOList(comments);
    }

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO commentRequest, ObjectId postId) {
        Comment comment = commentMapper.toComment(commentRequest);
        comment.setUserId(new ObjectId(commentRequest.getUserId()));
        comment.setPostId(postId);
        commentRepository.insert(comment);
        return commentMapper.toCommentResponseDTO(comment);
    }

    @Override
    public void updateComment(CommentRequestDTO comment, ObjectId id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isEmpty()){
            //TODO: NotFoundException
        }

        commentMapper.updateCommentFromDTO(comment, commentOptional.get());
        commentOptional.get().setUserId(new ObjectId(comment.getUserId()));
        commentRepository.save(commentOptional.get());
    }

    @Override
    public void deleteComment(ObjectId id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isEmpty()){
            //TODO: NotFoundException
        }

        commentRepository.delete(commentOptional.get());
    }
}
