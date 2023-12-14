package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.exception.AlreadyExistException;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.model.UserDTO;
import com.sfeiropensource.schoolapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) throws AlreadyExistException {
        Optional<User> existingUser = userRepository.findByIdun(userDTO.getId());
        if (existingUser.isPresent()) {
            throw new AlreadyExistException("The user attached to this ID already exist");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.toUserDTO(userRepository.save(objectMapper.toUser(userDTO))));
    }

    /**
     * Fetch a specific user attached to idun
     *
     * @param idun int
     * @return ResponseEntity<UserDTO>
     */
    public ResponseEntity<UserDTO> get(int idun) throws NotFoundException {
        Optional<User> user = userRepository.findByIdun(idun);
        if (user.isPresent()) {
            return ResponseEntity.ok(objectMapper.toUserDTO(user.get()));
        }
        throw new NotFoundException("No User is attached to this idun");
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
     * @param idun    int
     * @param userDTO UserDTO
     * @return ResponseEntity<UserDTO>
     */
    public ResponseEntity<UserDTO> update(int idun, UserDTO userDTO) throws NotFoundException {
        if (!userRepository.existsByIdun(idun)) {
            throw new NotFoundException("No User is attached to this idun");
        }
        return ResponseEntity.ok(objectMapper.toUserDTO(userRepository.save(objectMapper.toUser(userDTO))));
    }

    /**
     * Delete user inside the DB
     *
     * @param idun int
     * @return ResponseEntity<HttpStatus>
     */
    public ResponseEntity<HttpStatus> delete(int idun) {
        try {
            userRepository.deleteByIdun(idun);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
