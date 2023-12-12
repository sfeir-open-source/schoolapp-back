package com.sfeiropensource.schoolapp.service;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.exception.AlreadyExistException;
import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.mapper.ObjectMapper;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @param schoolDTO SchoolDTO
     * @return SchoolDTO
     */
    public ResponseEntity<SchoolDTO> saveSchool(SchoolDTO schoolDTO) throws AlreadyExistException {
        Optional<School> existingSchool = schoolRepository.findById(schoolDTO.getIdun());
        if (existingSchool.isPresent()) {
            throw new AlreadyExistException("The school attached to this ID already exist");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.toSchoolDTO(schoolRepository.save(objectMapper.toSchool(schoolDTO))));
    }

    /**
     * Fetch a specific school attached to id
     *
     * @param id int
     * @return ResponseEntity<SchoolDTO>
     */
    public ResponseEntity<SchoolDTO> get(int id) throws NotFoundException {
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
     * @param schoolDTO SchoolDTO
     * @return ResponseEntity<SchoolDTO>
     */
    public ResponseEntity<SchoolDTO> update(int id, SchoolDTO schoolDTO) throws NotFoundException {
        if (!schoolRepository.existsById(id)) {
            throw new NotFoundException("No School is attached to this id");
        }
        return ResponseEntity.ok(objectMapper.toSchoolDTO(schoolRepository.save(objectMapper.toSchool(schoolDTO))));
    }

    /**
     * Delete school inside the DB
     *
     * @param id int
     * @return ResponseEntity<HttpStatus>
     */
    public ResponseEntity<HttpStatus> delete(int id) {
        try {
            schoolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
