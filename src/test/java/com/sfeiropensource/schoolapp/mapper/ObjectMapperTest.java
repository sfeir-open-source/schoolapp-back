package com.sfeiropensource.schoolapp.mapper;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static com.sfeiropensource.schoolapp.utils.Objects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ObjectMapperTest {

    private final ObjectMapper objectMapper = Mappers.getMapper(ObjectMapper.class);

    @Test
    void toSchoolDTO() {
        School school = generateSchool();

        SchoolDTO expectedResult = generateSchoolDTO();

        SchoolDTO result = objectMapper.toSchoolDTO(school);

        assertEquals(expectedResult, result);
        assertNull(objectMapper.toSchoolDTO(null));

        // If no teachers are present, it must create a new empty array list.
        school.setTeachers(null);
        expectedResult.setTeachers(null);

        result = objectMapper.toSchoolDTO(school);

        assertEquals(expectedResult, result);

        school = School.builder().build();
        expectedResult = SchoolDTO.builder().build();

        result = objectMapper.toSchoolDTO(school);

        assertEquals(expectedResult, result);
    }

    @Test
    void newSchool() {
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();

        School expectedResult = generateSchool();
        expectedResult.setId(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        School result = objectMapper.newSchool(createSchoolDTO);

        assertEquals(expectedResult, result);
        assertNull(objectMapper.newSchool(null));

        // If no professor, it should return null for professor user object.
        createSchoolDTO.setProfessor(null);
        expectedResult.setProfessor(null);

        // If no teachers are present, it must create a new empty array list.
        createSchoolDTO.setTeachers(null);
        expectedResult.setTeachers(null);

        result = objectMapper.newSchool(createSchoolDTO);

        assertEquals(expectedResult, result);

        createSchoolDTO = CreateSchoolDTO.builder().build();
        expectedResult = School.builder().build();

        result = objectMapper.newSchool(createSchoolDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    void toUserDTO() {
        User user = generateUser();

        UserDTO expectedResult = generateUserDTO();

        UserDTO result = objectMapper.toUserDTO(user);

        assertEquals(expectedResult, result);
        assertNull(objectMapper.toUserDTO(null));
    }

    @Test
    void newUser() {
        CreateUserDTO createUserDTO = generateCreateUserDTO();

        User expectedResult = generateUser();
        expectedResult.setId(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        User result = objectMapper.newUser(createUserDTO);

        assertEquals(expectedResult, result);
        assertNull(objectMapper.newUser(null));
    }

    @Test
    void updateSchoolFromSchoolDto() {
        CreateSchoolDTO createSchoolDTO = generateCreateSchoolDTO();
        School result = School.builder().build();

        School expectedResult = generateSchool();
        expectedResult.setId(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        objectMapper.updateSchoolFromSchoolDto(createSchoolDTO, result);

        assertEquals(expectedResult, result);

        expectedResult = generateSchool();
        result = generateSchool();

        objectMapper.updateSchoolFromSchoolDto(createSchoolDTO, result);

        assertEquals(expectedResult, result);

        objectMapper.updateSchoolFromSchoolDto(null, result);

        assertEquals(expectedResult, result);

        result = School.builder().build();
        expectedResult = School.builder().build();

        objectMapper.updateSchoolFromSchoolDto(CreateSchoolDTO.builder().build(), result);

        assertEquals(expectedResult, result);

        result = School.builder().build();
        expectedResult = School.builder().teachers(List.of(User.builder().build())).build();

        objectMapper.updateSchoolFromSchoolDto(CreateSchoolDTO.builder().teachers(List.of(UserDTO.builder().build())).build(), result);

        assertEquals(expectedResult, result);
    }

    @Test
    void updateUserFromUserDto() {
        CreateUserDTO createUserDTO = generateCreateUserDTO();
        User result = User.builder().build();

        User expectedResult = generateUser();
        expectedResult.setId(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        objectMapper.updateUserFromUserDto(createUserDTO, result);

        assertEquals(expectedResult, result);

        expectedResult = generateUser();
        result = generateUser();

        objectMapper.updateUserFromUserDto(createUserDTO, result);

        assertEquals(expectedResult, result);

        objectMapper.updateUserFromUserDto(null, result);

        assertEquals(expectedResult, result);

        result = User.builder().build();
        expectedResult = User.builder().build();

        objectMapper.updateUserFromUserDto(CreateUserDTO.builder().build(), result);

        assertEquals(expectedResult, result);
    }

    @Test
    void constructExample() {
        SearchSchoolDTO searchSchoolDTO = generateSearchSchoolDTO();
        School expectedResult = generateSchool();

        expectedResult.setId(null);
        expectedResult.setImage(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        expectedResult.getProfessor().setId(null);
        expectedResult.getProfessor().setCreatedAt(null);
        expectedResult.getProfessor().setCreatedBy(null);
        expectedResult.getProfessor().setUpdatedAt(null);
        expectedResult.getProfessor().setUpdatedBy(null);
        expectedResult.getProfessor().setVersion(0);

        expectedResult.getTeachers().get(0).setId(null);
        expectedResult.getTeachers().get(0).setCreatedAt(null);
        expectedResult.getTeachers().get(0).setCreatedBy(null);
        expectedResult.getTeachers().get(0).setUpdatedAt(null);
        expectedResult.getTeachers().get(0).setUpdatedBy(null);
        expectedResult.getTeachers().get(0).setVersion(0);

        School result = objectMapper.constructExample(searchSchoolDTO);

        assertEquals(expectedResult, result);

        assertNull(objectMapper.constructExample((SearchSchoolDTO) null));
    }

    @Test
    void map() {
        String value = "value";

        List<String> expectedResult = List.of(value);

        List<String> result = objectMapper.map(value);

        assertEquals(expectedResult, result);

        expectedResult = List.of();
        result = objectMapper.map((String) null);

        assertEquals(expectedResult, result);
    }

    @Test
    void testConstructExample() {
        SearchUserDTO searchUserDTO = generateSearchUserDTO();
        User expectedResult = generateUser();

        expectedResult.setId(null);
        expectedResult.setCreatedAt(null);
        expectedResult.setCreatedBy(null);
        expectedResult.setUpdatedAt(null);
        expectedResult.setUpdatedBy(null);
        expectedResult.setVersion(0);

        User result = objectMapper.constructExample(searchUserDTO);

        assertEquals(expectedResult, result);

        assertNull(objectMapper.constructExample((SearchUserDTO) null));
    }

    @Test
    void testMap() {
        SearchUserDTO value = generateSearchUserDTO();
        User user = generateUser();

        user.setId(null);
        user.setCreatedAt(null);
        user.setCreatedBy(null);
        user.setUpdatedAt(null);
        user.setUpdatedBy(null);
        user.setVersion(0);

        List<User> expectedResult = List.of(user);

        List<User> result = objectMapper.map(value);

        assertEquals(expectedResult, result);

        expectedResult = List.of();
        result = objectMapper.map((SearchUserDTO) null);

        assertEquals(expectedResult, result);
    }
}