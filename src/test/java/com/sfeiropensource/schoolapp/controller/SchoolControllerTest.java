package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.model.CreateSchoolDTO;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.SearchSchoolDTO;
import com.sfeiropensource.schoolapp.service.SchoolService;
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

class SchoolControllerTest {

    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private SchoolController schoolController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        ResponseEntity<List<SchoolDTO>> expectedResult = ResponseEntity.ok(List.of());

        when(schoolService.getAll()).thenReturn(expectedResult);

        ResponseEntity<List<SchoolDTO>> result = schoolController.getAll();

        assertEquals(expectedResult, result);
    }

    @Test
    void search() {
        int pageNumber = 0;
        int pageSize = 10;
        SearchSchoolDTO searchSchoolDTO = generateSearchSchoolDTO();
        ResponseEntity<Page<SchoolDTO>> expectedResult = ResponseEntity.ok(new PageImpl<>(List.of()));

        when(schoolService.search(pageNumber, pageSize, searchSchoolDTO)).thenReturn(expectedResult);

        ResponseEntity<Page<SchoolDTO>> result = schoolController.search(searchSchoolDTO, pageNumber, pageSize);

        assertEquals(expectedResult, result);
    }

    @Test
    void add() {
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();
        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity.ok(generateSchoolDTO());

        when(schoolService.saveSchool(createSchoolDTO)).thenReturn(expectedResult);

        ResponseEntity<SchoolDTO> result = schoolController.add(createSchoolDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void get() throws NotFoundException {
        String id = "id";
        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity.ok(generateSchoolDTO());

        when(schoolService.get(id)).thenReturn(expectedResult);

        ResponseEntity<SchoolDTO> result = schoolController.get(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void update() throws NotFoundException {
        String id = "id";
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();
        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity.ok(generateSchoolDTO());

        when(schoolService.update(id, createSchoolDTO)).thenReturn(expectedResult);

        ResponseEntity<SchoolDTO> result = schoolController.update(id, createSchoolDTO);

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