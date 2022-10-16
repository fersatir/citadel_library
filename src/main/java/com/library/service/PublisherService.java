package com.library.service;

import com.library.domain.Author;
import com.library.domain.Publisher;
import com.library.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public void savePublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

    public Page<Publisher> getAllPublishersWithPage(Pageable pageable) {
      return  publisherRepository.findAllPublishersWithPage(pageable);
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
        //TODO -> Exception
    }

    public Publisher updatePublisherById(Long id, Publisher newPublisher) {
        Publisher foundPublisher = getPublisherById(id);
        foundPublisher.setName(newPublisher.getName());
        foundPublisher.setBuiltIn(newPublisher.getBuiltIn());

        publisherRepository.save(foundPublisher);
        return foundPublisher;
    }

    public Publisher deletePublisherById(Long id) {
        Publisher foundPublisher = getPublisherById(id);
        publisherRepository.deleteById(id);
        return foundPublisher;
    }
}
