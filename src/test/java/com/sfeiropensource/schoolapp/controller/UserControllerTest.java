package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.model.User;
import com.sfeiropensource.schoolapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        User user1 = new User(1, "John", "Doe", "john.doe@example.com", "user");
        User user2 = new User(2, "Jane", "Smith", "jane.smith@example.com", "admin");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testAdd() {
        // Arrange
        User user = new User(1, "John", "Doe", "john.doe@example.com", "user");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.add(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetExistingUser() {
        // Arrange
        int userId = 1;
        User user = new User(userId, "John", "Doe", "john.doe@example.com", "user");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<?> response = userController.get(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(user), response.getBody());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetNonExistingUser() {
        // Arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = userController.get(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No User is attached to this id", response.getBody());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testUpdateExistingUser() {
        // Arrange
        int userId = 1;
        User existingUser = new User(userId, "John", "Doe", "john.doe@example.com", "user");
        User updatedUser = new User(userId, "Jane", "Smith", "jane.smith@example.com", "admin");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        // Act
        ResponseEntity<String> response = userController.update(userId, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated", response.getBody());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void testUpdateNonExistingUser() {
        // Arrange
        int userId = 1;
        User updatedUser = new User(userId, "Jane", "Smith", "jane.smith@example.com", "admin");
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = userController.update(userId, updatedUser);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No User is attached to this id", response.getBody());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(0)).save(updatedUser);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        int userId = 1;

        // Act
        ResponseEntity<String> response = userController.delete(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted", response.getBody());
        verify(userRepository, times(1)).deleteById(userId);
    }
}
