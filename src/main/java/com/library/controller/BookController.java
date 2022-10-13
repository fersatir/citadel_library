package com.library.controller;

import com.library.dto.BookDTO;
import com.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDto){

        BookDTO book = bookService.createBook(bookDto);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO){

       BookDTO book = bookService.updateBookById(id,bookDTO);

        return new ResponseEntity<>(book,HttpStatus.OK);
    }


}
