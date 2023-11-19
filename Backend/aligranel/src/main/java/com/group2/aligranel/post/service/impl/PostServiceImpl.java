package com.group2.aligranel.post.service.impl;

import com.group2.aligranel.post.dto.request.PostRequestDTO;
import com.group2.aligranel.post.dto.response.PostResponseDTO;
import com.group2.aligranel.post.mapper.PostMapper;
import com.group2.aligranel.post.model.Post;
import com.group2.aligranel.post.repository.PostRepository;
import com.group2.aligranel.post.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;

    @Override
    public PostResponseDTO getPostById(ObjectId id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if(!postOptional.isPresent()){
            //TODO: NotFoundException
        }

        PostResponseDTO response = postMapper.toPostResponseDTO(postOptional.get());
        return response;
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> response = postMapper.toPostResponseDTOList(posts);
        return response;
    }

    @Override
    public List<PostResponseDTO> getPostsByUserId(ObjectId userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        List<PostResponseDTO> response = postMapper.toPostResponseDTOList(posts);
        return response;
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequest, ObjectId userId) {
        if(postRepository.existsByContent(postRequest.getContent())){
            //TODO DuplicatedTitleException
        }
        Post post = postMapper.toPost(postRequest);
        post.setProductId(new ObjectId(postRequest.getProductId()));
        post.setUserId(userId);
        postRepository.insert(post);
        return postMapper.toPostResponseDTO(post);
    }

    @Override
    public void updatePost(PostRequestDTO post, ObjectId id) {
        Optional<Post> postToBeUpdated = postRepository.findById(id);
        if(!postToBeUpdated.isPresent()){
            //TODO: NotFoundException
        }

        postMapper.updatePostFromDTO(post, postToBeUpdated.get());
        postToBeUpdated.get().setProductId(new ObjectId(post.getProductId()));
        postRepository.save(postToBeUpdated.get());
    }

    @Override
    public void deletePost(ObjectId id) {
        Optional<Post> postToBeDeleted = postRepository.findById(id);
        if(!postToBeDeleted.isPresent()){
            //TODO: NotFoundException
        }
        postRepository.delete(postToBeDeleted.get());
    }
}
