package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.model.CreateUserDTO;
import com.sfeiropensource.schoolapp.model.SearchUserDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import com.sfeiropensource.schoolapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.sfeiropensource.schoolapp.utils.Objects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService schoolService;

    @InjectMocks
    private UserController schoolController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        ResponseEntity<List<UserDTO>> expectedResult = ResponseEntity.ok(List.of());

        when(schoolService.getAll()).thenReturn(expectedResult);

        ResponseEntity<List<UserDTO>> result = schoolController.getAll();

        assertEquals(expectedResult, result);
    }

    @Test
    void search() {
        int pageNumber = 0;
        int pageSize = 10;
        SearchUserDTO searchUserDTO = generateSearchUserDTO();
        ResponseEntity<Page<UserDTO>> expectedResult = ResponseEntity.ok(new PageImpl<>(List.of()));

        when(schoolService.search(pageNumber, pageSize, searchUserDTO)).thenReturn(expectedResult);

        ResponseEntity<Page<UserDTO>> result = schoolController.search(searchUserDTO, pageNumber, pageSize);

        assertEquals(expectedResult, result);
    }

    @Test
    void add() {
        CreateUserDTO createUserDTO = generateCreateUserDTO();
        ResponseEntity<UserDTO> expectedResult = ResponseEntity.ok(generateUserDTO());

        when(schoolService.add(createUserDTO)).thenReturn(expectedResult);

        ResponseEntity<UserDTO> result = schoolController.add(createUserDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void get() throws NotFoundException {
        String id = "id";
        ResponseEntity<UserDTO> expectedResult = ResponseEntity.ok(generateUserDTO());

        when(schoolService.get(id)).thenReturn(expectedResult);

        ResponseEntity<UserDTO> result = schoolController.get(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void update() throws NotFoundException {
        String id = "id";
        CreateUserDTO createUserDTO = generateCreateUserDTO();
        ResponseEntity<UserDTO> expectedResult = ResponseEntity.ok(generateUserDTO());

        when(schoolService.update(id, createUserDTO)).thenReturn(expectedResult);

        ResponseEntity<UserDTO> result = schoolController.update(id, createUserDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void delete() {
        String id = "id";
        ResponseEntity<HttpStatus> expectedResult = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(schoolService.delete(id)).thenReturn(expectedResult);

        ResponseEntity<HttpStatus> result = schoolController.delete(id);

        assertEquals(expectedResult, result);
    }
}