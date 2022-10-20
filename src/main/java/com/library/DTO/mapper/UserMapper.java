package com.library.dto.mapper;

import com.library.domain.User;

import com.library.dto.UserCreateDTO;
import com.library.dto.UserDTO;
import com.library.dto.requests.AdminUpdateUserRequest;
import com.library.dto.requests.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    UserCreateDTO userToUserCreateDTO(User user);

    User userCreateDTOToUser(UserCreateDTO userCreateDTO);

    List<UserDTO> map(List<User>user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User adminUpdateUserRequest(AdminUpdateUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User updateUserBySelf(UserUpdateRequest request);



}
