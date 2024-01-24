package com.sfeiropensource.schoolapp.mapper;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.CreateSchoolDTO;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ObjectMapper {
    SchoolDTO toSchoolDTO(School school);

    School newSchool(CreateSchoolDTO createSchoolDTO);

    School toSchool(SchoolDTO schoolDTO);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateSchoolFromSchoolDto(SchoolDTO schoolDTO, @MappingTarget School school);

    @Mapping(target = "id", ignore = true)
    void updateUserFromUserDto(UserDTO userDTO, @MappingTarget User user);
}
