package com.library.dto.response;

import com.library.domain.Book;
import com.library.domain.Loan;
import com.library.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanAmountCategoryResponse {

    private String category;
    private Long bookAmount;


    public LoanAmountCategoryResponse(Long bookAmount, Book book){
      this.bookAmount = bookAmount;
        this.category = book.getCategory().getName();


    }
}
