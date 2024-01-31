package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.mapper.ObjectMapperImpl;
import com.sfeiropensource.schoolapp.model.CreateSchoolDTO;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.SearchSchoolDTO;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
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

class SchoolServiceTest {
    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapperImpl();

    @InjectMocks
    private SchoolService schoolService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveSchool() {
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();
        SchoolDTO schoolDTO = generateSchoolDTO();
        School school = generateSchool();

        when(schoolRepository.save(school)).thenReturn(school);
        when(objectMapper.newSchool(createSchoolDTO)).thenReturn(school);
        when(objectMapper.toSchoolDTO(school)).thenReturn(schoolDTO);

        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(schoolDTO);

        ResponseEntity<SchoolDTO> result = this.schoolService.saveSchool(createSchoolDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void get() throws NotFoundException {
        String id = "id";
        School school = generateSchool();
        Optional<School> optionalSchool = Optional.of(school);
        SchoolDTO schoolDTO = generateSchoolDTO();

        when(schoolRepository.findById(id)).thenReturn(optionalSchool);
        when(objectMapper.toSchoolDTO(school)).thenReturn(schoolDTO);

        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity.ok(schoolDTO);

        ResponseEntity<SchoolDTO> result = schoolService.get(id);

        assertEquals(expectedResult, result);

        String invalidId = "bad_id";
        when(schoolRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> schoolService.get(invalidId));
    }

    @Test
    void getAll() {
        School school = generateSchool();
        SchoolDTO schoolDTO = generateSchoolDTO();

        when(schoolRepository.findAll()).thenReturn(List.of(school));
        when(objectMapper.toSchoolDTO(school)).thenReturn(schoolDTO);

        ResponseEntity<List<SchoolDTO>> expectedResult = ResponseEntity.ok(List.of(schoolDTO));
        ResponseEntity<List<SchoolDTO>> result = schoolService.getAll();

        assertEquals(expectedResult, result);
    }

    @Test
    void search() {
        int pageNumber = 0;
        int pageSize = 10;
        SearchSchoolDTO searchSchoolDTO = generateSearchSchoolDTO();
        School school = generateSchool();
        SchoolDTO schoolDTO = generateSchoolDTO();

        Page<School> page = new PageImpl<>(List.of(school));
        when(objectMapper.constructExample(searchSchoolDTO)).thenReturn(school);
        when(schoolRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(page);
        when(objectMapper.toSchoolDTO(school)).thenReturn(schoolDTO);

        ResponseEntity<Page<SchoolDTO>> expectedResult = ResponseEntity.ok(new PageImpl<>(List.of(schoolDTO)));

        ResponseEntity<Page<SchoolDTO>> result = schoolService.search(pageNumber, pageSize, searchSchoolDTO);

        assertEquals(expectedResult, result);

    }

    @Test
    void update() throws NotFoundException {
        String id = "id";
        School school = generateSchool();
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();
        SchoolDTO schoolDTO = generateSchoolDTO();

        when(schoolRepository.findById(id)).thenReturn(Optional.of(school));
        when(schoolRepository.save(school)).thenReturn(school);
        when(objectMapper.toSchoolDTO(school)).thenReturn(schoolDTO);

        ResponseEntity<SchoolDTO> expectedResult = ResponseEntity.ok(schoolDTO);
        ResponseEntity<SchoolDTO> result = schoolService.update(id, createSchoolDTO);

        verify(objectMapper).updateSchoolFromSchoolDto(createSchoolDTO, school);
        verify(schoolRepository).save(school);

        assertEquals(expectedResult, result);

        String invalidId = "bad_id";
        when(schoolRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> schoolService.update(invalidId, createSchoolDTO));
    }

    @Test
    void delete() {
        String id = "id";

        ResponseEntity<HttpStatus> expectedResult = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        ResponseEntity<HttpStatus> result = schoolService.delete(id);

        verify(schoolRepository).deleteById(id);

        assertEquals(expectedResult, result);

        doThrow(NoSuchElementException.class).when(schoolRepository).deleteById(id);

        expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        result = schoolService.delete(id);

        assertEquals(expectedResult, result);
    }

}