package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.exception.AlreadyExistException;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.interceptor.ExceptionInterceptor;
import com.sfeiropensource.schoolapp.model.UserDTO;
import com.sfeiropensource.schoolapp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("users")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "User", description = "User controller")
public class UserController implements ExceptionInterceptor {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieve all users from database
     *
     * @return List<User>
     */
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll() {
        return userService.getAll();
    }

    /**
     * Add a user to the database
     *
     * @param userDTO UserDTO
     * @return UserDTO - User is returned to provide new generated id
     */
    @PostMapping("/add")
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) throws AlreadyExistException {
        return userService.saveUser(userDTO);
    }

    /**
     * Retrieve a specific user attached to an id
     *
     * @param id int - Unique identifier of a user
     * @return User | String
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable String id) throws NotFoundException {
        return userService.get(id);
    }

    /**
     * Update a user
     *
     * @param id      int - Unique identifier of a user
     * @param userDTO UserDTO - updated
     * @return String
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody UserDTO userDTO) throws NotFoundException {
        return userService.update(id, userDTO);
    }

    /**
     * Delete a user
     *
     * @param id int - Unique identifier of a user
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
