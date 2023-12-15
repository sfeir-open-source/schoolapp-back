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
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private List<SchoolDTO> schools;
}
