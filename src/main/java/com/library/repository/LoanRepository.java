package com.library.repository;


import com.library.domain.Loan;
import com.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

  //  @Query("SELECT u FROM User u WHERE u.id = ?1")
  //  boolean existsByUserId(User user);
}
