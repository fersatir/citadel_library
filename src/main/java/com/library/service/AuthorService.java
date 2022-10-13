package com.library.service;

import com.library.dto.requests.AuthorRequest;
import com.library.domain.Author;
import com.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository authorRepository;


    public Author createAuthor(AuthorRequest authorRequest) {

        Author author = new Author();

        author.setName(authorRequest.getName());
        author.setBuiltIn(authorRequest.getBuiltIn());

        authorRepository.save(author);

        return author;
    }
    public Author getAuthorWithId(Long id) {

        Author author = authorRepository.findById(id).orElse(null);

        return author;
    }

}
