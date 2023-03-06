package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository
        extends MongoRepository<School, Integer> {
}