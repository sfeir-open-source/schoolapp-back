package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.model.User;
import com.sfeiropensource.schoolapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve all users from database
     *
     * @return List<User>
     */
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Add a user to the database
     *
     * @param user User
     * @return User - User is returned to provide new generated id
     */
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        User savedSchool = userRepository.save(user);
        return ResponseEntity.ok(savedSchool);
    }

    /**
     * Retrieve a specific user attached to an id
     *
     * @param id int - Unique identifier of a user
     * @return User | String
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User is attached to this id");
    }

    /**
     * Update a user
     *
     * @param id            int - Unique identifier of a user
     * @param updatedSchool User - updated
     * @return String
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody User updatedSchool) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User is attached to this id");
        }
        // TODO: Search why example update each property at a time and doesn't just save the object.
        userRepository.save(updatedSchool);
        return ResponseEntity.ok("User updated");
    }

    /**
     * Delete a user
     *
     * @param id int - Unique identifier of a user
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}
