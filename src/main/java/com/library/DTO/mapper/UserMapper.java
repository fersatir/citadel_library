package com.library.dto.mapper;

import com.library.domain.User;

import com.library.dto.UserCreateDTO;
import com.library.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    UserCreateDTO userToUserCreateDTO(User user);

    User userCreateDTOToUser(UserCreateDTO userCreateDTO);



}
