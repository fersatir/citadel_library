package com.library.dto.mapper;

import com.library.domain.User;
import com.library.dto.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserCreateDTO userToUserCreateDTO(User user);

    User userCreateDTOToUser(UserCreateDTO userCreateDTO);

}
