package com.library.service;

import com.library.domain.*;
import com.library.domain.enums.RoleType;
import com.library.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class TestService {

    PublisherRepository publisherRepository;
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    LoanRepository loanRepository;
    RoleRepository roleRepository;
    UserRepository userRepository;

    public Publisher createPublisher() {

        Publisher p = new Publisher();
        p.setName("ahmet");
        p.setBuiltIn(true);

        publisherRepository.save(p);

        return p;
    }

    public Author createAuthor() {

        Author au = new Author("John Coffe", false);

        authorRepository.save(au);

        return au;
    }

    public User createUser() {

        User us = new User();

        LocalDateTime time = LocalDateTime.now();
        Date dt = new Date();

        Set<Role> roles = new HashSet<>();
        roles.add(createRole());

        us.setCreateDate(time);
        us.setAddress("istanbul Anadolu Yakası");
        us.setCreateDate(time);
        us.setEmail("abc@gmail.com");
        us.setFirstName("Ahmet Gozel");
        us.setLastName("Ok");
        us.setPassword("12345");
        us.setPhone("123-333-4444");
        us.setResetPasswordCode("12345");
        us.setScore(1);
        us.setRoles(roles);
        us.setBirthDate(dt);

        userRepository.save(us);

        return us;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Loan createLoan() {

        LocalDateTime time = LocalDateTime.now();

        Loan lo = new Loan();

        lo.setBook(createBook());
        lo.setExpireDate(time);
        lo.setLoanDate(time);
        lo.setNotes("test note");
        lo.setReturnDate(time);
        lo.setUser(createUser());

        loanRepository.save(lo);

        return lo;
    }


    public Role createRole() {

        Role ro = new Role();
        ro.setName(RoleType.ROLE_MEMBER);
        Set<Role> roles = new HashSet<>();
        roles.add(ro);

        roleRepository.save(ro);

        return ro;
    }

    public Category createCategory() {

        Category cu = new Category("Korku", false, 2022);

        categoryRepository.save(cu);

        return cu;
    }

    public Book createBook() {

        LocalDateTime time = LocalDateTime.now();

        Book bo = new Book();

        bo.setAuthor(createAuthor());
        bo.setIsbn("123-22-55555-22-1");
        bo.setName("İnce Mehmet");
        bo.setPageCount(300);
        bo.setCreateDate(time);
        bo.setPublishDate(2005);
        bo.setPublisher(createPublisher());
        bo.setShelfCode("FE-222");
        bo.setCategory(createCategory());

        bookRepository.save(bo);

        return bo;
    }


}