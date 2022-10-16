package com.library.repository;

import com.library.domain.Book;
import com.library.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT new com.library.dto.BookDTO(book) from Book book where book.name = :query or book.category.id = :cat or book.author.id = :author or book.publisher.id = :publisher")
    Page<BookDTO> findByQueryAndCatAndAuthorAndPublisherWithPage(@Param("query") Optional<String> query,
                                                                 @Param("cat") Optional<Long> cat,
                                                                 @Param("author") Optional<Long> author,
                                                                 @Param("publisher") Optional<Long> publisher, Pageable pageable);

    @Query("SELECT u FROM Book u WHERE u.category.id = ?1")
    Book existsBookCategoryId(Long id);

}
