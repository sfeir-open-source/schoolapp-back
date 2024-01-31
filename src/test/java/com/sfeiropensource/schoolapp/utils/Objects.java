package com.sfeiropensource.schoolapp.utils;

import com.sfeiropensource.schoolapp.entity.School;
import com.sfeiropensource.schoolapp.entity.User;
import com.sfeiropensource.schoolapp.model.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Objects {
    public static SearchSchoolDTO generateSearchSchoolDTO() {
        return SearchSchoolDTO.builder()
                .title("title")
                .publicSummary("publicSummary")
                .duration("duration")
                .objectives("objectives")
                .prerequisites("prerequisites")
                .document("document")
                .githubLink("githubLink")
                .user(generateSearchUserDTO())
                .status("status")
                .build();
    }

    public static SearchUserDTO generateSearchUserDTO() {
        return SearchUserDTO.builder()
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .role("role")
                .build();
    }

    public static CreateSchoolDTO generateCreateSchoolDTO() {
        return CreateSchoolDTO.builder()
                .title("title")
                .image("image")
                .publicSummary("publicSummary")
                .duration("duration")
                .objectives(new ArrayList<>(List.of("objectives")))
                .prerequisites(new ArrayList<>(List.of("prerequisites")))
                .document("document")
                .githubLink("githubLink")
                .teachers(new ArrayList<>(List.of(generateUserDTO())))
                .professor(generateUserDTO())
                .status("status")
                .build();
    }

    public static SchoolDTO generateSchoolDTO() {
        return SchoolDTO.builder()
                .id("id")
                .title("title")
                .image("image")
                .publicSummary("publicSummary")
                .duration("duration")
                .objectives(new ArrayList<>(List.of("objectives")))
                .prerequisites(new ArrayList<>(List.of("prerequisites")))
                .document("document")
                .githubLink("githubLink")
                .teachers(new ArrayList<>(List.of(generateUserDTO())))
                .professor(generateUserDTO())
                .status("status")
                .createdBy("createdBy")
                .createdAt(Instant.EPOCH)
                .updatedBy("createdBy")
                .updatedAt(Instant.EPOCH)
                .version(1)
                .build();
    }

    public static CreateUserDTO generateCreateUserDTO() {
        return CreateUserDTO.builder()
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .role("role")
                .build();
    }

    public static UserDTO generateUserDTO() {
        return UserDTO.builder()
                .id("id")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .role("role")
                .createdBy("createdBy")
                .createdAt(Instant.EPOCH)
                .updatedBy("createdBy")
                .updatedAt(Instant.EPOCH)
                .version(1)
                .build();
    }

    public static School generateSchool() {
        return School.builder()
                .id("id")
                .title("title")
                .image("image")
                .publicSummary("publicSummary")
                .duration("duration")
                .objectives(new ArrayList<>(List.of("objectives")))
                .prerequisites(new ArrayList<>(List.of("prerequisites")))
                .document("document")
                .githubLink("githubLink")
                .teachers(new ArrayList<>(List.of(generateUser())))
                .professor(generateUser())
                .status("status")
                .createdBy("createdBy")
                .createdAt(Instant.EPOCH)
                .updatedBy("createdBy")
                .updatedAt(Instant.EPOCH)
                .version(1)
                .build();
    }

    public static User generateUser() {
        return User.builder()
                .id("id")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .role("role")
                .createdBy("createdBy")
                .createdAt(Instant.EPOCH)
                .updatedBy("createdBy")
                .updatedAt(Instant.EPOCH)
                .version(1)
                .build();
    }
}
