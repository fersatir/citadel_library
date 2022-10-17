package com.library.dto.response;


import com.library.domain.Loan;
import com.library.domain.User;
import com.library.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserLoansResponse {

    private long id;

    private long userId;

    private long bookId;

    private User user;

    public UserLoansResponse(Loan loan) {
        this.id = loan.getId();
        this.userId = loan.getUser().getId();
        this.bookId =loan.getBook().getId() ;
        this.user = loan.getUser();
    }
}
