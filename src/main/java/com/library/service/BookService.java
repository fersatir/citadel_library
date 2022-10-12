package com.library.service;

import com.library.domain.Book;
import com.library.dto.BookDTO;
import com.library.dto.mapper.BookMapper;
import com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookService {

    BookRepository bookRepository;
    AuthorService authorService;
    CategoryService categoryService;
    PublisherService publisherService;
    BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, PublisherService publisherService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
        this.bookMapper = bookMapper;
    }

    public BookDTO createBook(BookDTO bookDto) {

        Book book = bookMapper.bookDTOToBook(bookDto);
        book.setCategory(categoryService.getCategoryWithId(bookDto.getCategory_id()));
        book.setAuthor(authorService.getAuthorWithId(bookDto.getAuthor_id()));
        book.setPublisher(publisherService.getPublisherWithId(bookDto.getPublisher_id()));

        bookRepository.save(book);

        bookDto.setId(book.getId());

        return bookDto;
    }

}
