package com.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

    @PostMapping("/add")

    public ResponseEntity<CRResponse> saveCar(@PathVariable String imageId,@Valid @RequestBody CarDTO carDTO){
        carService.saveCar(carDTO, imageId);

        CRResponse response=new CRResponse();
        response.setMessage("Car successfully saved");
        response.setSuccess(true);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }