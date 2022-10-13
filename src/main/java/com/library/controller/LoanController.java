package com.library.controller;

import com.library.DTO.LoanDTO;
import com.library.dto.BookDTO;
import com.library.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PostMapping("/add")
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanDTO loanDTO){

        LoanDTO loan =loanService.createLoan(loanDTO);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }


}
