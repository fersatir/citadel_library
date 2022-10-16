package com.library.controller;

import com.library.domain.Category;
import com.library.domain.Loan;
import com.library.dto.LoanDTO;
import com.library.dto.response.LoanResponse;
import com.library.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PostMapping("/add")
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanDTO loanDTO){

        LoanDTO loan =loanService.createLoan(loanDTO);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping()
    public ResponseEntity<Page<LoanResponse>> getAuthenticatedUserLoansWithPage(HttpServletRequest request,
                                                          @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                          @RequestParam(required = false,value = "size", defaultValue = "20") int size,
                                                          @RequestParam(required = false,value = "sort", defaultValue = "loanDate") String prop,
                                                          @RequestParam(required = false,value = "direction", defaultValue = "DESC") Sort.Direction direction){

        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Long idLogin = (Long) request.getAttribute("id");
        Page<LoanResponse> loanAutUser = loanService.getAuthenticatedUserLoans(pageable,idLogin);

        return ResponseEntity.ok(loanAutUser);
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getAuthenticatedUserLoanWithId(HttpServletRequest request, @PathVariable  Long id){

        Long idLogin = (Long) request.getAttribute("id");
        LoanResponse loanAutUser = loanService.getAuthenticatedUserLoanWithId(idLogin,id);

        return ResponseEntity.ok(loanAutUser);
    }


}
