package com.group2.aligranel.user.service;

import com.group2.aligranel.user.dto.request.UserLoginRequestDTO;
import com.group2.aligranel.user.dto.request.UserRequestDTO;
import com.group2.aligranel.user.dto.response.UserResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    UserResponseDTO getUserById(ObjectId id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createUser(UserRequestDTO user);
    void updateUser(UserRequestDTO user, ObjectId id);
    void deleteUser(ObjectId id);
    void loginUser(UserLoginRequestDTO userLogin);
}
