package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository
        extends MongoRepository<School, Integer> {
}