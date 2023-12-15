package com.sfeiropensource.schoolapp.mapper;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ObjectMapper {
    SchoolDTO toSchoolDTO(School school);

    School toSchool(SchoolDTO schoolDTO);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
