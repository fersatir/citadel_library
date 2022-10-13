package com.library.service;

import com.library.DTO.LoanDTO;
import com.library.domain.Book;
import com.library.domain.Loan;
import com.library.domain.User;
import com.library.dto.mapper.LoanMapper;
import com.library.exception.BadRequestException;
import com.library.exception.message.ErrorMessage;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class LoanService {

    LoanRepository loanRepository;
    LoanMapper loanMapper;

    BookRepository bookRepository;
    UserRepository userRepository;

    public LoanDTO createLoan(LoanDTO loanDTO){

        checkLoanTimeIsCorrect(loanDTO.getLoanDate(), loanDTO.getReturnDate());

        Loan loan = new Loan();

        Book book = bookRepository.findById(loanDTO.getBookId()).orElse(null);
        loan.setBook(book);


        User user = userRepository.findById(loanDTO.getUserId()).orElse(null);
        loan.setUser(user);

        loan = loanMapper.loanDTOToLoan(loanDTO);

        loanRepository.save(loan);
        loanDTO.setId(loan.getId());
        return loanDTO;

    }


    private void checkLoanTimeIsCorrect(LocalDateTime loanDate, LocalDateTime returnDate) {
        LocalDateTime now = LocalDateTime.now();

        if (loanDate.isBefore(now)) {
            throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
        }
        boolean isEqual = loanDate.isEqual(returnDate);
        boolean isBefore = loanDate.isBefore(returnDate);

        if (isEqual || !isBefore) {
            throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
        }
    }


}
