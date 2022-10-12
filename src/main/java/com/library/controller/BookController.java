package com.library.controller;

import com.library.DTO.BookDTO;
import com.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDto){

        BookDTO book = bookService.createAuthor(bookDto);

        return ResponseEntity.ok(book);
    }


}
