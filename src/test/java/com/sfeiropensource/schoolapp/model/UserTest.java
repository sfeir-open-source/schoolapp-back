package com.sfeiropensource.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testUserAttributes() {
        // Arrange
        int id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String email = "john.doe@example.com";
        String role = "user";

        // Act
        User user = new User(id, firstname, lastname, email, role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(firstname, user.getFirstname());
        assertEquals(lastname, user.getLastname());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
    }

    // Add more test cases for other methods or behaviors in the User class
}