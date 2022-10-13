package com.library.dto.mapper;

import com.library.domain.User;
import com.library.dto.UserDTO;
import com.library.dto.requests.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

}
