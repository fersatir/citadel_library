package com.library.dto.mapper;
import com.library.DTO.LoanDTO;

import com.library.domain.Loan;


import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface LoanMapper {

    LoanDTO loanToLoanDTO(Loan loan);
    Loan loanDTOToLoan(LoanDTO loanDTO);

}
