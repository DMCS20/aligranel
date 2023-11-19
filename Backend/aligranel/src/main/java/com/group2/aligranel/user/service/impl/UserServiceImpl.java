package com.group2.aligranel.user.service.impl;

import com.group2.aligranel.shared.exception.ResourceNotFoundException;
import com.group2.aligranel.shared.exception.ValidationException;
import com.group2.aligranel.user.dto.request.UserRequestDTO;
import com.group2.aligranel.user.dto.response.UserResponseDTO;
import com.group2.aligranel.user.dto.request.UserLoginRequestDTO;
import com.group2.aligranel.user.mapper.UserMapper;
import com.group2.aligranel.user.model.User;
import com.group2.aligranel.user.repository.UserRepository;
import com.group2.aligranel.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO getUserById(ObjectId id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }

        return userMapper.toUserResponseDTO(userOptional.get());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseDTOList(users);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ValidationException("Username already exists");
        }
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new ValidationException("Email already exists");
        }

        User user = userMapper.toUser(userRequest);
        userRepository.insert(user);
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public void updateUser(UserRequestDTO userRequest, ObjectId id) {
        Optional<User> userToBeUpdated = userRepository.findById(id);
        if(userToBeUpdated.isEmpty()){
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }

        userMapper.updateUserFromDTO(userRequest, userToBeUpdated.get());
        userRepository.save(userToBeUpdated.get());
    }

    @Override
    public void deleteUser(ObjectId id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
        else{
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public void loginUser(UserLoginRequestDTO userLogin) {
        Optional<User> user = userRepository.findByUsername(userLogin.getUsername());
        if(user.isEmpty()){
            throw new ValidationException("Invalid username or password");
        }

        if(!user.get().getPassword().equals(userLogin.getPassword())){
            throw new ValidationException("Invalid username or password");
        }
    }
}
