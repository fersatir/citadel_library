package com.library.dto.response;

import com.library.domain.Book;
import com.library.domain.Loan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanUpdateResponse {
    private Long id;
    private Long userId;
    private Long bookId;
    private String notes;
    private LocalDateTime loanDate;
    private LocalDateTime expireDate;
    private LocalDateTime returnDate;


}
