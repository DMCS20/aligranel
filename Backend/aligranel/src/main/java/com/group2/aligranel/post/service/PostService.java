package com.group2.aligranel.post.service;

import com.group2.aligranel.post.dto.request.PostRequestDTO;
import com.group2.aligranel.post.dto.response.PostResponseDTO;
import com.group2.aligranel.post.model.Post;
import org.bson.types.ObjectId;

import java.util.List;

public interface PostService {
    PostResponseDTO getPostById(ObjectId id);
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> getPostsByUserId(ObjectId userId);
    PostResponseDTO createPost(PostRequestDTO post, ObjectId userId);
    void updatePost(PostRequestDTO post, ObjectId id);
    void deletePost(ObjectId id);
}
