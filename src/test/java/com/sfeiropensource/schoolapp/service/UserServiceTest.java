package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.mapper.ObjectMapperImpl;
import com.sfeiropensource.schoolapp.model.CreateUserDTO;
import com.sfeiropensource.schoolapp.model.SearchUserDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import com.sfeiropensource.schoolapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.sfeiropensource.schoolapp.utils.Objects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapperImpl();

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void add() {
        CreateUserDTO createUserDTO = generateCreateUserDTO();
        UserDTO userDTO = generateUserDTO();
        User user = generateUser();

        when(userRepository.save(user)).thenReturn(user);
        when(objectMapper.newUser(createUserDTO)).thenReturn(user);
        when(objectMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<UserDTO> expectedResult = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDTO);

        ResponseEntity<UserDTO> result = this.userService.add(createUserDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void get() throws NotFoundException {
        String id = "id";
        User user = generateUser();
        Optional<User> optionalUser = Optional.of(user);
        UserDTO userDTO = generateUserDTO();

        when(userRepository.findById(id)).thenReturn(optionalUser);
        when(objectMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<UserDTO> expectedResult = ResponseEntity.ok(userDTO);

        ResponseEntity<UserDTO> result = userService.get(id);

        assertEquals(expectedResult, result);

        String invalidId = "bad_id";
        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.get(invalidId));
    }

    @Test
    void getAll() {
        User user = generateUser();
        UserDTO userDTO = generateUserDTO();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(objectMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> expectedResult = ResponseEntity.ok(List.of(userDTO));
        ResponseEntity<List<UserDTO>> result = userService.getAll();

        assertEquals(expectedResult, result);
    }

    @Test
    void search() {
        int pageNumber = 0;
        int pageSize = 10;
        SearchUserDTO searchUserDTO = generateSearchUserDTO();
        User user = generateUser();
        UserDTO userDTO = generateUserDTO();

        Page<User> page = new PageImpl<>(List.of(user));
        when(objectMapper.constructExample(searchUserDTO)).thenReturn(user);
        when(userRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(page);
        when(objectMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<Page<UserDTO>> expectedResult = ResponseEntity.ok(new PageImpl<>(List.of(userDTO)));

        ResponseEntity<Page<UserDTO>> result = userService.search(pageNumber, pageSize, searchUserDTO);

        assertEquals(expectedResult, result);

    }

    @Test
    void update() throws NotFoundException {
        String id = "id";
        User user = generateUser();
        CreateUserDTO createUserDTO = generateCreateUserDTO();
        UserDTO userDTO = generateUserDTO();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(objectMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<UserDTO> expectedResult = ResponseEntity.ok(userDTO);
        ResponseEntity<UserDTO> result = userService.update(id, createUserDTO);

        verify(objectMapper).updateUserFromUserDto(createUserDTO, user);
        verify(userRepository).save(user);

        assertEquals(expectedResult, result);

        String invalidId = "bad_id";
        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(invalidId, createUserDTO));
    }

    @Test
    void delete() {
        String id = "id";

        ResponseEntity<HttpStatus> expectedResult = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        ResponseEntity<HttpStatus> result = userService.delete(id);

        verify(userRepository).deleteById(id);

        assertEquals(expectedResult, result);

        doThrow(NoSuchElementException.class).when(userRepository).deleteById(id);

        expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        result = userService.delete(id);

        assertEquals(expectedResult, result);
    }

}