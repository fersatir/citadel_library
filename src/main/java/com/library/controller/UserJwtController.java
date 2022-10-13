package com.library.controller;

import com.library.dto.UserDTO;
import com.library.dto.requests.RegisterRequest;
import com.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class UserJwtController {

  private  UserService userService;

    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request){

     UserDTO userDTO = userService.register(request);

     return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
}
