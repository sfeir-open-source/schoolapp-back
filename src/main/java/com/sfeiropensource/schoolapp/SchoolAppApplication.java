package com.sfeiropensource.schoolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.sfeiropensource.schoolapp.repository")
public class SchoolAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolAppApplication.class, args);
    }
}
