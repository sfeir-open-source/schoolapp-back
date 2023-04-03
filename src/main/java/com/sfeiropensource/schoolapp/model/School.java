package com.sfeiropensource.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schools")
public class School {    // Attributes
    @Id
    private ObjectId _id;
    private String title;
    // Optional ?
    private String image;
    private String publicSummary;
    private String duration;
    private List<String> objectives;
    private List<String> prerequisites;
    private String document;
    private String githubLink;
    private List<User> teachers;
    private User professor;
    private String status;
}
