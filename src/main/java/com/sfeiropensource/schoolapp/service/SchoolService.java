package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.model.CreateSchoolDTO;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final ObjectMapper objectMapper;

    SchoolService(SchoolRepository schoolRepository, ObjectMapper objectMapper) {
        this.schoolRepository = schoolRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Save provided school into the database
     *
     * @param createSchoolDTO SchoolDTO
     * @return SchoolDTO
     */
    public ResponseEntity<SchoolDTO> saveSchool(CreateSchoolDTO createSchoolDTO) {

        School school = objectMapper.newSchool(createSchoolDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.toSchoolDTO(schoolRepository.save(school)));
    }

    /**
     * Fetch a specific school attached to id
     *
     * @param id int
     * @return ResponseEntity<SchoolDTO>
     */
    public ResponseEntity<SchoolDTO> get(String id) throws NotFoundException {
        Optional<School> school = schoolRepository.findById(id);
        if (school.isPresent()) {
            return ResponseEntity.ok(objectMapper.toSchoolDTO(school.get()));
        }
        throw new NotFoundException("No School is attached to this id");
    }

    /**
     * Fetch all schools
     *
     * @return ResponseEntity<List < SchoolDTO>>
     */
    public ResponseEntity<List<SchoolDTO>> getAll() {
        return ResponseEntity.ok(schoolRepository.findAll().stream().map(objectMapper::toSchoolDTO).toList());
    }

    /**
     * Update school inside the DB
     *
     * @param id        int
     * @param schoolDTO CreateSchoolDTO
     * @return ResponseEntity<SchoolDTO>D
     */
    public ResponseEntity<SchoolDTO> update(String id, CreateSchoolDTO schoolDTO) throws NotFoundException {
        Optional<School> request = schoolRepository.findById(id);
        if (request.isEmpty()) {
            throw new NotFoundException("No School is attached to this id");
        }

        School school = request.get();

        // Update school from dto.
        objectMapper.updateSchoolFromSchoolDto(schoolDTO, school);

        try {
            // Save.
            school = schoolRepository.save(school);
        } catch (Exception exception) {
            log.error(exception.getLocalizedMessage());
        }

        return ResponseEntity.ok(objectMapper.toSchoolDTO(school));
    }

    /**
     * Delete school inside the DB
     *
     * @param id int
     * @return ResponseEntity<HttpStatus>
     */
    public ResponseEntity<HttpStatus> delete(String id) {
        try {
            schoolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
