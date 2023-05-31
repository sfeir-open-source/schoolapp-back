package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.model.School;
import com.sfeiropensource.schoolapp.model.User;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
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

class SchoolControllerTest {

    User teacher = new User(1, "teacher", "sfeir", "sfeir@sfeir.com", "teacher");
    User professor = new User(1, "professor", "sfeir", "sfeir@sfeir.com", "professor");
    @Mock
    private SchoolRepository schoolRepository;
    @InjectMocks
    private SchoolController schoolController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        School school1 = new School(1, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        School school2 = new School(2, "School 2", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        List<School> schools = Arrays.asList(school1, school2);
        when(schoolRepository.findAll()).thenReturn(schools);

        // Act
        ResponseEntity<List<School>> response = schoolController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schools, response.getBody());
        verify(schoolRepository, times(1)).findAll();
    }

    @Test
    void testAdd() {
        // Arrange
        School school = new School(1, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        when(schoolRepository.save(school)).thenReturn(school);

        // Act
        ResponseEntity<School> response = schoolController.add(school);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(school, response.getBody());
        verify(schoolRepository, times(1)).save(school);
    }

    @Test
    void testGetExistingSchool() {
        // Arrange
        int schoolId = 1;
        School school = new School(schoolId, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

        // Act
        ResponseEntity<?> response = schoolController.get(schoolId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(school), response.getBody());
        verify(schoolRepository, times(1)).findById(schoolId);
    }

    @Test
    void testGetNonExistingSchool() {
        // Arrange
        int schoolId = 1;
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = schoolController.get(schoolId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No School is attached to this id", response.getBody());
        verify(schoolRepository, times(1)).findById(schoolId);
    }

    @Test
    void testUpdateExistingSchool() {
        // Arrange
        int schoolId = 1;
        School existingSchool = new School(schoolId, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        School updatedSchool = new School(schoolId, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(existingSchool));
        when(schoolRepository.save(updatedSchool)).thenReturn(updatedSchool);

        // Act
        ResponseEntity<String> response = schoolController.update(schoolId, updatedSchool);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("School updated", response.getBody());
        verify(schoolRepository, times(1)).findById(schoolId);
        verify(schoolRepository, times(1)).save(updatedSchool);
    }

    @Test
    void testUpdateNonExistingSchool() {
        // Arrange
        int schoolId = 1;
        School updatedSchool = new School(schoolId, "School 1", "image", "public summary", "1 day", List.of("objective 1"), List.of("prerequisite 1"), "document", "github link", List.of(teacher), professor, "status");
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = schoolController.update(schoolId, updatedSchool);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No School is attached to this id", response.getBody());
        verify(schoolRepository, times(1)).findById(schoolId);
        verify(schoolRepository, times(0)).save(updatedSchool);
    }

    @Test
    void testDeleteSchool() {
        // Arrange
        int schoolId = 1;

        // Act
        ResponseEntity<String> response = schoolController.delete(schoolId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("School deleted", response.getBody());
        verify(schoolRepository, times(1)).deleteById(schoolId);
    }
}
