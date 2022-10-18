package com.library.controller;

import com.library.dto.ReportMostPopularBookDTO;
import com.library.dto.ReportStatisticDTO;
import com.library.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<ReportMostPopularBookDTO>> getMostPopularBooksWithPage(
           ){

        List<ReportMostPopularBookDTO> mostPopularBook = reportService.getMostPopularBooksWithPage();

        return ResponseEntity.ok(mostPopularBook);
    }








}
