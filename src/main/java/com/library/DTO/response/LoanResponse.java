package com.library.dto.response;

import com.library.domain.Book;
import com.library.domain.Loan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanResponse {


    private Long id;

    private LocalDateTime loanDate;

    private LocalDateTime expireDate;

    private LocalDateTime returnDate;

    private Book book;


    public LoanResponse(Loan loan) {
        this.id = loan.getId();
        this.loanDate = loan.getLoanDate();
        this.expireDate = loan.getExpireDate();
        this.returnDate = loan.getReturnDate();
        this.book = loan.getBook();
    }
}
