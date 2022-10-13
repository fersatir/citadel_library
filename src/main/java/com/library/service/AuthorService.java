package com.library.service;

import com.library.dto.AuthorDTO;
import com.library.domain.Author;
import com.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository authorRepository;


    public Author createAuthor(AuthorDTO authorDTO) {

        Author author = new Author();

        author.setName(authorDTO.getName());
        author.setBuiltIn(authorDTO.getBuiltIn());

        authorRepository.save(author);

        return author;
    }

}
