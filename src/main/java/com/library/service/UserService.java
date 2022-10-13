package com.library.service;

import com.library.domain.Role;
import com.library.domain.User;
import com.library.domain.enums.RoleType;
import com.library.dto.UserCreateDTO;
import com.library.dto.mapper.UserMapper;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;
    private UserMapper userMapper;



    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) {

        //TODO kayıt yapmaya çalışan kullanıcının role bilgisine göre (admin, employee), kayıt edilecek kullanıcıya role atanacak

        User user = userMapper.userCreateDTOToUser(userCreateDTO);

        Role ro = new Role();
        ro.setName(roleRepository.findById(userCreateDTO.getRoleId()).get().getName());
        Set<Role> roles = new HashSet<>();
        roles.add(ro);

        userRepository.save(user);

        userCreateDTO.setId(user.getId());

        return userCreateDTO;

    }
}
