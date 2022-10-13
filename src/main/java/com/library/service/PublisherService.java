package com.library.service;

import com.library.domain.Author;
import com.library.domain.Publisher;
import com.library.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public void savePublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

}
