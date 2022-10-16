package com.library.dto.mapper;

import com.library.domain.Loan;
import com.library.dto.LoanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface LoanMapper {

    LoanDTO loanToLoanDTO(Loan loan);

    @Mapping(target = "expireDate", ignore = true)
    Loan loanDTOToLoan(LoanDTO loanDTO);

}
