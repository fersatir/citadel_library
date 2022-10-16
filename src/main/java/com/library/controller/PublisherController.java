package com.library.controller;

import com.library.domain.Publisher;
import com.library.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/publishers")
    public ResponseEntity<Page<Publisher>> getAllPublishers(@RequestParam("page") int page,
                                                            @RequestParam("size") int size,
                                                            @RequestParam("sort") String prop,
                                                            @RequestParam("type") Direction direction){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction,prop));
        Page<Publisher> publisherPage = publisherService.getAllPublishersWithPage(pageable);
        return new ResponseEntity<>(publisherPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id){
        Publisher publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisherById(@PathVariable Long id, @Valid @RequestBody Publisher newPublisher){
        Publisher publisher = publisherService.updatePublisherById(id, newPublisher);
        return ResponseEntity.ok(publisher);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisherById(@PathVariable Long id){
        Publisher publisher = publisherService.deletePublisherById(id);
        return ResponseEntity.ok(publisher);
    }
}
