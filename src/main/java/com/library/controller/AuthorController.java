package com.library.controller;

import com.library.DTO.requests.AuthorRequest;
import com.library.domain.Author;
import com.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/author")
@RestController
public class AuthorController {

    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorRequest authorRequest){

        Author author = authorService.createAuthor(authorRequest);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }


}
