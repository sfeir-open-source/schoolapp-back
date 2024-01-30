package com.sfeiropensource.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchSchoolDTO {
    private String title;
    private String publicSummary;
    private String duration;
    private String objectives;
    private String prerequisites;
    private String document;
    private String githubLink;
    private String teacher;
    private String professor;
    private String status;
    private SearchUserDTO user;
}

