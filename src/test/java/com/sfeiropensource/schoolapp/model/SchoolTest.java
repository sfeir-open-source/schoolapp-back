package com.sfeiropensource.schoolapp.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchoolTest {

    @Test
    void testSchoolAttributes() {
        // Arrange
        int id = 1;
        String title = "Example School";
        String image = "example.jpg";
        String publicSummary = "This is an example school";
        String duration = "3 months";
        List<String> objectives = Arrays.asList("Objective 1", "Objective 2");
        List<String> prerequisites = Arrays.asList("Prerequisite 1", "Prerequisite 2");
        String document = "document.pdf";
        String githubLink = "https://github.com/example";
        List<User> teachers = List.of(new User(1, "John", "Doe", "john.doe@example.com", "teacher"));
        User professor = new User(2, "Jane", "Smith", "jane.smith@example.com", "professor");
        String status = "active";

        // Act
        School school = new School(id, title, image, publicSummary, duration, objectives, prerequisites,
                document, githubLink, teachers, professor, status);

        // Assert
        assertEquals(id, school.getId());
        assertEquals(title, school.getTitle());
        assertEquals(image, school.getImage());
        assertEquals(publicSummary, school.getPublicSummary());
        assertEquals(duration, school.getDuration());
        assertEquals(objectives, school.getObjectives());
        assertEquals(prerequisites, school.getPrerequisites());
        assertEquals(document, school.getDocument());
        assertEquals(githubLink, school.getGithubLink());
        assertEquals(teachers, school.getTeachers());
        assertEquals(professor, school.getProfessor());
        assertEquals(status, school.getStatus());
    }

    // Add more test cases for other methods or behaviors in the School class
}
