package com.sfeiropensource.schoolapp.mapper;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.SchoolDTO;
import com.sfeiropensource.schoolapp.model.UserDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ObjectMapper {
    @Mapping(target = "id", source = "idun")
    SchoolDTO toSchoolDTO(School school);

    @Mapping(target = "idun", source = "id")
    School toSchool(SchoolDTO schoolDTO);

    @Mapping(target = "id", source = "idun")
    UserDTO toUserDTO(User user);

    @Mapping(target = "idun", source = "id")
    User toUser(UserDTO userDTO);
}
