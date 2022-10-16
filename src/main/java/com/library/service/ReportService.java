package com.library.service;

import com.library.domain.*;
import com.library.dto.response.ReportMostPopularBookDTO;
import com.library.dto.response.ReportStatisticDTO;
import com.library.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

     List<Book> books =  bookRepository.findAll();
     List<Author> authors =  authorRepository.findAll();
     List<Publisher> publishers =  publisherRepository.findAll();
     List<Category> categories =  categoryRepository.findAll();
     List<Loan> loans =  loanRepository.findAll();

     ReportStatisticDTO statistics = new ReportStatisticDTO();

     statistics.setBooksNumber(books.size());
     statistics.setAuthorNumber(authors.size());
     statistics.setPublisherNumber(publishers.size());
     statistics.setCategoryNumber(categories.size());
     statistics.setLoansNumber(loans.size());

        return statistics;
    }


    //TODO Most popular books get
    public Page<ReportMostPopularBookDTO> getMostPopularBooksWithPage(Pageable pageable) {

        return null;
    }
}
