package com.library.controller;

import com.library.domain.Author;
import com.library.dto.UserCreateDTO;
import com.library.dto.requests.AuthorRequest;
import com.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;













    @PostMapping("/add")
    public ResponseEntity<UserCreateDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){

        UserCreateDTO user = userService.createUser(userCreateDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
