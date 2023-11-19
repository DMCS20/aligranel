package com.group2.aligranel.post.mapper;

import com.group2.aligranel.post.dto.request.PostRequestDTO;
import com.group2.aligranel.post.dto.response.PostResponseDTO;
import com.group2.aligranel.post.model.Post;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponseDTO toPostResponseDTO(Post post);
    @Mapping(target = "productId", ignore = true)
    Post toPost(PostRequestDTO postRequestDTO);
    List<PostResponseDTO> toPostResponseDTOList(List<Post> posts);
    @Mapping(target = "productId", ignore = true)
    List<Post> toPostList(List<PostRequestDTO> postRequestDTOList);
    ObjectId toObjectId(String id);

    @Mapping(target = "productId", ignore = true)
    void updatePostFromDTO(PostRequestDTO postRequestDTO, @MappingTarget Post post);

}
