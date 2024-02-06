package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.model.CreateUserDTO;
import com.sfeiropensource.schoolapp.model.SearchUserDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import com.sfeiropensource.schoolapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    /**
     * Save provided user into the database
     *
     * @param userDTO UserDTO
     * @return UserDTO
     */
    public ResponseEntity<UserDTO> add(CreateUserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.toUserDTO(userRepository.save(objectMapper.newUser(userDTO))));
    }

    /**
     * Fetch a specific user attached to id
     *
     * @param id int
     * @return ResponseEntity<UserDTO>
     */
    public ResponseEntity<UserDTO> get(String id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(objectMapper.toUserDTO(user.get()));
        }
        throw new NotFoundException("No User is attached to this id");
    }

    /**
     * Fetch all users
     *
     * @return ResponseEntity<List < UserDTO>>
     */
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(objectMapper::toUserDTO).toList());
    }

    /**
     * Update user inside the DB
     *
     * @param id      int
     * @param userDTO CreateUserDTO
     * @return ResponseEntity<UserDTO>
     */
    public ResponseEntity<UserDTO> update(String id, CreateUserDTO userDTO) throws NotFoundException {
        Optional<User> request = userRepository.findById(id);
        if (request.isEmpty()) {
            throw new NotFoundException("No User is attached to this id");
        }

        User user = request.get();

        // Update user from dto.
        objectMapper.updateUserFromUserDto(userDTO, user);

        // Save.
        userRepository.save(user);

        return this.get(id);
    }

    /**
     * Search among schools with criteria
     *
     * @param pageNumber    {int}
     * @param pageSize      {int}
     * @param searchUserDTO {SearchSchoolDTO}
     * @return ResponseEntity<Page < SchoolDTO>>
     */
    public ResponseEntity<Page<UserDTO>> search(int pageNumber, int pageSize, SearchUserDTO searchUserDTO) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Create a School example with OR logic between different fields
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("role", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        User user = objectMapper.constructExample(searchUserDTO);
        
        Example<User> example = Example.of(user, exampleMatcher);

        return ResponseEntity.ok(userRepository.findAll(example, pageable).map(objectMapper::toUserDTO));
    }

    /**
     * Delete user inside the DB
     *
     * @param id int
     * @return ResponseEntity<HttpStatus>
     */
    public ResponseEntity<HttpStatus> delete(String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
