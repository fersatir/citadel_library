package com.library.repository;

import com.library.domain.Book;
import com.library.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT u FROM Book u WHERE u.category.id = ?1")
    Book existsBookCategoryId(Long id);

}
