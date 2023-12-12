package com.sfeiropensource.schoolapp.entity;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @Null
    private ObjectId _id;
    private int idun;
    private String firstname;
    private String lastname;
    private String email;
    // User moderator admin
    private String role;
}

