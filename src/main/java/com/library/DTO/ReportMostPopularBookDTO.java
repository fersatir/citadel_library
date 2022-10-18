package com.library.dto;

import com.library.domain.Loan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportMostPopularBookDTO {

    private Long id;

    private String name;

    private String isbn;

    private Integer pageCount;


    private Long amount;

    public ReportMostPopularBookDTO(Long id, String name, String isbn, Integer pageCount, Long amount) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.amount = amount;
    }
}
