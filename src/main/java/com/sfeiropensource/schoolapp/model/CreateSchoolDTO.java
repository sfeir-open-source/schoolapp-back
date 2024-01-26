package com.sfeiropensource.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSchoolDTO {
    private String title;
    private String image;
    private String publicSummary;
    private String duration;
    private List<String> objectives;
    private List<String> prerequisites;
    private String document;
    private String githubLink;
    private List<UserDTO> teachers;
    private UserDTO professor;
    private String status;
}

