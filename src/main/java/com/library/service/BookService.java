package com.library.service;

import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Category;
import com.library.domain.Publisher;
import com.library.dto.BookDTO;
import com.library.dto.mapper.BookMapper;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import com.library.repository.PublisherRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;

    AuthorRepository authorRepository;

    CategoryRepository categoryRepository;

    PublisherRepository publisherRepository;
    BookMapper bookMapper;



    public BookDTO createBook(BookDTO bookDto) {

        Book book = bookMapper.bookDTOToBook(bookDto);

        Category category = categoryRepository.findById(bookDto.getCategory_id()).orElse(null);
        Author author = authorRepository.findById(bookDto.getAuthor_id()).orElse(null);
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher_id()).orElse(null);


        book.setCategory(category);
        book.setAuthor(author);
        book.setPublisher(publisher);

        bookRepository.save(book);

        bookDto.setId(book.getId());

        return bookDto;
    }


    public BookDTO updateBookById(Long id, BookDTO bookDTO) {

        Book foundBook = bookRepository.findById(id).orElse(null);

        if(foundBook.getBuiltIn()){
            throw new RuntimeException("It is not permitted to change");
        }

        bookDTO.setId(id);

        foundBook = bookMapper.bookDTOToBook(bookDTO);

        Category category = categoryRepository.findById(bookDTO.getCategory_id()).orElse(null);
        Author author = authorRepository.findById(bookDTO.getAuthor_id()).orElse(null);
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher_id()).orElse(null);

        foundBook.setCategory(category);
        foundBook.setAuthor(author);
        foundBook.setPublisher(publisher);

        bookRepository.save(foundBook);

        return bookDTO;
    }
}
