package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.exception.NotFoundException;
import com.sfeiropensource.schoolapp.interceptor.ExceptionInterceptor;
import com.sfeiropensource.schoolapp.model.CreateSchoolDTO;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.SearchSchoolDTO;
import com.sfeiropensource.schoolapp.service.SchoolService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("schools")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "School", description = "School controller")
public class SchoolController implements ExceptionInterceptor {

    private final SchoolService schoolService;

    /**
     * Retrieve all schools from database
     *
     * @return List<School>
     */
    @GetMapping({"/", ""})
    public ResponseEntity<List<SchoolDTO>> getAll() {
        return schoolService.getAll();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<SchoolDTO>> search(@Valid @RequestBody SearchSchoolDTO searchSchoolDTO,
                                                  @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return schoolService.search(pageNumber, pageSize, searchSchoolDTO);
    }

    /**
     * Add a school to the database
     *
     * @param schoolDTO UserDTO
     * @return UserDTO - User is returned to provide new generated id
     */
    @PostMapping("/add")
    public ResponseEntity<SchoolDTO> add(@RequestBody CreateSchoolDTO schoolDTO) {
        return schoolService.saveSchool(schoolDTO);
    }

    /**
     * Retrieve a specific school attached to an id
     *
     * @param id int - Unique identifier of a school
     * @return School | String
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<SchoolDTO> get(@PathVariable String id) throws NotFoundException {
        return schoolService.get(id);
    }

    /**
     * Update a school
     *
     * @param id        int - Unique identifier of a school
     * @param schoolDTO SchoolDTO - updated
     * @return String
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<SchoolDTO> update(@PathVariable("id") String id, @RequestBody CreateSchoolDTO schoolDTO) throws NotFoundException {
        return schoolService.update(id, schoolDTO);
    }

    /**
     * Delete a school
     *
     * @param id int - Unique identifier of a school
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return schoolService.delete(id);
    }
}
