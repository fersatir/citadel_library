package com.library.repository;


import com.library.domain.Loan;
import com.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

  //  @Query("SELECT u FROM User u WHERE u.id = ?1")
    boolean existsByUserId(User user);


    @Query("SELECT u FROM Loan u WHERE u.user.id = ?1")
    List<Loan> expireDate(Long id);


}
