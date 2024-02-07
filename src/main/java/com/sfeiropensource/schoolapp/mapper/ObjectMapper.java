package com.sfeiropensource.schoolapp.mapper;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ObjectMapper {
    SchoolDTO toSchoolDTO(School school);

    School newSchool(CreateSchoolDTO createSchoolDTO);

    UserDTO toUserDTO(User user);

    User newUser(CreateUserDTO userDTO);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateSchoolFromSchoolDto(CreateSchoolDTO schoolDTO, @MappingTarget School school);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateUserFromUserDto(CreateUserDTO userDTO, @MappingTarget User user);

    @Mapping(target = "objectives", source = "objectives")
    @Mapping(target = "prerequisites", source = "prerequisites")
    @Mapping(target = "professor", source = "user")
    @Mapping(target = "teachers", source = "user")
    School constructExample(SearchSchoolDTO searchSchoolDTO);

    default List<String> map(String value) {
        if (value == null) return List.of();
        return List.of(value);
    }

    User constructExample(SearchUserDTO user);

    default List<User> map(SearchUserDTO value) {
        if (value == null) return List.of();
        return List.of(constructExample(value));
    }
}
