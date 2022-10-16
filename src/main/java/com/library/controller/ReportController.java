package com.library.controller;

import com.library.dto.response.ReportMostPopularBookDTO;
import com.library.dto.response.ReportStatisticDTO;
import com.library.service.ReportService;
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

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
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
    public ResponseEntity<Page<ReportMostPopularBookDTO>> getMostPopularBooksWithPage(
            @RequestParam(required = false,value = "page", defaultValue = "0") int page,
            @RequestParam(required = false,value = "size", defaultValue = "20") int size,
            @RequestParam(required = false,value = "sort", defaultValue = "amount") String prop,
            @RequestParam(required = false,value = "direction", defaultValue = "DESC") Sort.Direction direction){

        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<ReportMostPopularBookDTO> mostPopularBook = reportService.getMostPopularBooksWithPage(pageable);

        return ResponseEntity.ok(mostPopularBook);
    }







}
