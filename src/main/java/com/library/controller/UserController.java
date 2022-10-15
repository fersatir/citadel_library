package com.library.controller;


import com.library.dto.UserCreateDTO;
import com.library.dto.UserDTO;
import com.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PostMapping("/add")
    public ResponseEntity<UserCreateDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){

        UserCreateDTO user = userService.createUser(userCreateDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users= userService.getAllUsers();

        return ResponseEntity.ok(users);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
    public ResponseEntity<UserDTO> getUserById(HttpServletRequest request){
      Long id = (Long) request.getAttribute("id");
      UserDTO userDTO = userService.getUser(id);
      return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<UserDTO> getUserByIdAdmin(@PathVariable Long id){
    UserDTO userDTO=userService.getUser(id);
    return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long id){
    UserDTO userDTO=userService.removeById(id);
    return ResponseEntity.ok(userDTO);
    }

}
