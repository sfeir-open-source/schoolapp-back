package com.sfeiropensource.schoolapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schools")
public class School {
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    // School title
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
    // List of teachers
    @DocumentReference
    private List<User> teachers;
    // Professor of the school
    @DocumentReference
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
    private int version;
}
