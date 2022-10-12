package com.library.controller;

import com.library.domain.Publisher;
import com.library.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> createPublisher(@Valid @RequestBody Publisher publisher){
        publisherService.savePublisher(publisher);

        Map<String,Object> response = new HashMap<>();
        response.put("message","Publisher create successfully");
        response.put("status","true");
        response.put("publisher", publisher);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
