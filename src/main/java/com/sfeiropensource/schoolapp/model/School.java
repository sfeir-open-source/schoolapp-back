package com.sfeiropensource.schoolapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "School")
public class School {    // Attributes
    @Id private int id;
    private String title;
    private String public_summary;
    private String github_link;
}
