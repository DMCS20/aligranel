package com.group2.aligranel.user.mapper;

import com.group2.aligranel.user.dto.request.UserRequestDTO;
import com.group2.aligranel.user.dto.response.UserResponseDTO;
import com.group2.aligranel.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toUserResponseDTO(User user);
    User toUser(UserRequestDTO userRequest);

    List<UserResponseDTO> toUserResponseDTOList(List<User> users);
    List<User> toUserList(List<UserRequestDTO> userRequestList);

    void updateUserFromDTO(UserRequestDTO userRequest, @MappingTarget User user);
}
