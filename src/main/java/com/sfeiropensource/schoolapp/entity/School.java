package com.sfeiropensource.schoolapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schools")
public class School {
    @Id
    private ObjectId _id;
    private int idun;
    private String title;
    // URL link to image
    private String image;
    // Summary of what the school is talking about
    private String publicSummary;
    // Duration (1h30 or 30min for example)
    private String duration;
    // Learn java, or discover test end ot end
    private List<String> objectives;
    // Basic knowledge on front development or knowledge on project developments
    private List<String> prerequisites;
    // name of document
    private String document;
    // GitHub link to repository with exercises
    private String githubLink;
    // List of users (teachers)
    private List<User> teachers;
    private User professor;
    // Actual status of the school (wip live deprecated)
    private String status;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    private long version;
}
