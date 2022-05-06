package com.coffee.house.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

    @Test
    public void testInvalidEmail(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email email = new Email("wrongFormat");
        });
    }

    @Test
    public void testValidEmail(){
        Email email = new Email("test@gmail.com");
        assertTrue(email.getAddress().equals("test@gmail.com"));
    }

    @Test
    public void testEqualityEmail(){
        Email email = new Email("test@gmail.com");
        Email email2 = new Email("test@gmail.com");
        assertTrue(email.equals(email2));
    }

}