package com.library.dto.mapper;

import com.library.domain.Loan;
import com.library.dto.LoanDTO;
import com.library.dto.response.LoanUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface LoanMapper {

    LoanUpdateResponse loanToLoanDTO(Loan loan);

    @Mapping(target = "expireDate", ignore = true)
    Loan loanDTOToLoan(LoanDTO loanDTO);

}
