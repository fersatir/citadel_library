package com.library.controller;

import com.library.dto.BookDTO;
import com.library.dto.ReportMostBorrowersDTO;
import com.library.dto.ReportMostPopularBookDTO;
import com.library.dto.ReportStatisticDTO;
import com.library.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;

    // Tüm istatistiki Genel Verileri Getirir
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @GetMapping("/all")
    public ResponseEntity<ReportStatisticDTO> getAllStatistic(){
        ReportStatisticDTO statistics =  reportService.getAllStatistic();

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    //Most popular Books

    @GetMapping()
    public ResponseEntity<Page<ReportMostPopularBookDTO>> getMostPopularBooksWithPage( @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                                                       @RequestParam(required = false,value = "size", defaultValue = "20") int size
                                                                                       ){
        Pageable pageable = PageRequest.of(page,size);
        Page<ReportMostPopularBookDTO> mostPopularBook = reportService.getMostPopularBooksWithPage(pageable);

        return ResponseEntity.ok(mostPopularBook);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @GetMapping("/expired-books")
    public ResponseEntity<Page<BookDTO>> getExpiredBooks(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                            @RequestParam(required = false,value = "size", defaultValue = "20") int size){

        Pageable pageable = PageRequest.of(page,size);
        Page<BookDTO> unreturned = reportService.getExpiredBooksWithPage(pageable,LocalDateTime.now());

        return ResponseEntity.ok(unreturned);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @GetMapping("/unreturned-books")
    public ResponseEntity<Page<BookDTO>> getUnreturnedBooks(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                         @RequestParam(required = false,value = "size", defaultValue = "20") int size){

        Pageable pageable = PageRequest.of(page,size);
        Page<BookDTO> unreturned = reportService.getUnreturnedBooksWithPage(pageable, LocalDateTime.now());

        return ResponseEntity.ok(unreturned);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @GetMapping("/most-borrowers")
    public ResponseEntity<Page<ReportMostBorrowersDTO>> getMostBorrowers(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                                         @RequestParam(required = false,value = "size", defaultValue = "20") int size)
    {

        Pageable pageable = PageRequest.of(page,size);

        Page<ReportMostBorrowersDTO> mostBorrowers = reportService.getMostBorrowersWithPage(pageable);

        return ResponseEntity.ok(mostBorrowers);
    }

    }
