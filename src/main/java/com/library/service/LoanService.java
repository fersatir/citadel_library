package com.library.service;

import com.library.DTO.requests.LoanRequest;
import com.library.domain.Loan;
import com.library.exception.BadRequestException;
import com.library.exception.message.ErrorMessage;
import com.library.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoanService {

    LoanRepository loanRepository;

    public void createLoan(LoanRequest loanRequest){

        checkLoanTimeIsCorrect(loanRequest.getLoanDate(),loanRequest.getReturnDate());

        Loan loan = new Loan();

        loan.setLoanDate(loanRequest.getLoanDate());
        loan.setBook(loanRequest.getBook());
        loan.setExpireDate(loanRequest.getExpireDate());
        loan.setNotes(loan.getNotes());
        loan.setReturnDate(loanRequest.getReturnDate());
        loan.setUser(loanRequest.getUser());

        loanRepository.save(loan);

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
