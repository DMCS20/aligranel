package com.group2.aligranel.comment.mapper;

import com.group2.aligranel.comment.dto.CommentRequestDTO;
import com.group2.aligranel.comment.dto.CommentResponseDTO;
import com.group2.aligranel.comment.model.Comment;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponseDTO toCommentResponseDTO(Comment comment);
    @Mapping(target = "userId", ignore = true)
    Comment toComment(CommentRequestDTO commentRequestDTO);
    List<CommentResponseDTO> toCommentResponseDTOList(List<Comment> comments);
    @Mapping(target = "userId", ignore = true)
    List<Comment> toCommentList(List<CommentRequestDTO> commentRequestDTOList);

    @Mapping(target = "userId", ignore = true)
    void updateCommentFromDTO(CommentRequestDTO commentRequestDTO,@MappingTarget Comment comment);
}
