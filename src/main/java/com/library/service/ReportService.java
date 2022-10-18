package com.library.service;

import com.library.domain.*;
import com.library.dto.ReportMostPopularBookDTO;
import com.library.dto.ReportStatisticDTO;
import com.library.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class ReportService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;
    CategoryRepository categoryRepository;
    LoanRepository loanRepository;

    //TODO Eksik kısımlar tamamlanacak ReportStatisticDTO referans alınacak
    public ReportStatisticDTO getAllStatistic() {

        int books = bookRepository.findAll().size();
        int authors = authorRepository.findAll().size();
        int publishers = publisherRepository.findAll().size();
        int categories = categoryRepository.findAll().size();
        int loans = loanRepository.findAll().size();

        ReportStatisticDTO statistics = new ReportStatisticDTO();

        statistics.setBooksNumber(books);
        statistics.setAuthorNumber(authors);
        statistics.setPublisherNumber(publishers);
        statistics.setCategoryNumber(categories);
        statistics.setLoansNumber(loans);

        return statistics;
    }

    @Transactional
    public List<ReportMostPopularBookDTO> getMostPopularBooksWithPage() {

       List<ReportMostPopularBookDTO> allLoans = loanRepository.mostPopulars();

        return allLoans;
    }



}
