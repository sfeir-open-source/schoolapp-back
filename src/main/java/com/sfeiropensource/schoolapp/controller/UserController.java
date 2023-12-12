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
     * @return UserDTO - User is returned to provide new generated idun
     */
    @PostMapping("/add")
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) throws AlreadyExistException {
        return userService.saveUser(userDTO);
    }

    /**
     * Retrieve a specific user attached to an idun
     *
     * @param idun int - Unique identifier of a user
     * @return User | String
     */
    @GetMapping("/get/{idun}")
    public ResponseEntity<UserDTO> get(@PathVariable int idun) throws NotFoundException {
        return userService.get(idun);
    }

    /**
     * Update a user
     *
     * @param idun    int - Unique identifier of a user
     * @param userDTO UserDTO - updated
     * @return String
     */
    @PutMapping(value = "/update/{idun}")
    public ResponseEntity<UserDTO> update(@PathVariable("idun") int idun, @RequestBody UserDTO userDTO) throws NotFoundException {
        return userService.update(idun, userDTO);
    }

    /**
     * Delete a user
     *
     * @param idun int - Unique identifier of a user
     * @return String
     */
    @DeleteMapping("/delete/{idun}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int idun) {
        return userService.delete(idun);
    }
}
