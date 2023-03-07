package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.model.School;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("schools")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    /**
     * Retrieve all schools from database
     *
     * @return List<School>
     */
    @GetMapping("/")
    public ResponseEntity<List<School>> getAll() {
        return ResponseEntity.ok(schoolRepository.findAll());
    }

    /**
     * Add a school to the database
     *
     * @param school School
     * @return School - School is returned to provide new generated id
     */
    @PostMapping("/add")
    public ResponseEntity<School> add(@RequestBody School school) {
        School savedSchool = schoolRepository.save(school);
        return ResponseEntity.ok(savedSchool);
    }

    /**
     * Retrieve a specific school attached to an id
     *
     * @param id int - Unique identifier of a school
     * @return School | String
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        Optional<School> school = schoolRepository.findById(id);
        if (school.isPresent()) {
            return ResponseEntity.ok(school);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No School is attached to this id");
    }

    /**
     * Update a school
     *
     * @param id            int - Unique identifier of a school
     * @param updatedSchool School - updated
     * @return String
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody School updatedSchool) {
        Optional<School> school = schoolRepository.findById(id);
        if (school.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No School is attached to this id");
        }
        // TODO: Search why example update each property at a time and doesn't just save the object.
        schoolRepository.save(updatedSchool);
        return ResponseEntity.ok("School updated");
    }

    /**
     * Delete a school
     *
     * @param id int - Unique identifier of a school
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        schoolRepository.deleteById(id);
        return ResponseEntity.ok("School deleted");
    }
}
