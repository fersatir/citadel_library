package com.library.service;

import com.library.domain.enums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCreate {

    @Autowired
    TestService s;

    @Test
    public void roleTest() {

        Assertions.assertEquals(RoleType.ROLE_MEMBER, s.createRole().getName());

        Assertions.assertNotNull(s.createRole());

    }

    @Test
    public void userTest() {

        Assertions.assertEquals("Ahmet Gozel", s.createUser().getFirstName());

        Assertions.assertNotNull(s.createUser());

        Assertions.assertFalse(s.createUser().getBuiltIn());

    }


    @Test
    public void authorTest() {

        Assertions.assertEquals("John Coffe", s.createAuthor().getName());

        Assertions.assertNotNull(s.createAuthor());

        Assertions.assertFalse(s.createAuthor().getBuiltIn());

    }

    @Test
    public void categoryTest() {

        Assertions.assertEquals("Korku", s.createCategory().getName());

        Assertions.assertNotNull(s.createCategory());

        Assertions.assertFalse(s.createCategory().getBuiltIn());

    }

    @Test
    public void publisherTest() {

        Assertions.assertEquals("ahmet", s.createPublisher().getName());

        Assertions.assertNotNull(s.createPublisher());

        Assertions.assertTrue(s.createPublisher().getBuiltIn());

    }

    @Test
    public void bookTest() {

        Assertions.assertEquals("İnce Mehmet", s.createBook().getName());

        Assertions.assertNotNull(s.createBook());

        Assertions.assertTrue(s.createBook().getActive());


    }

    @Test
    public void loanTest() {

        Assertions.assertEquals("test note", s.createLoan().getNotes());

        Assertions.assertNotNull(s.createLoan());

    }


}