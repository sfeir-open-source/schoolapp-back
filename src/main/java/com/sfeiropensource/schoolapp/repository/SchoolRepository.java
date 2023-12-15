package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.entity.School;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {

    @Aggregation({
            "{$lookup: {from: 'users', localField: 'teachers', foreignField: '_id', as: 'teachers'}}"
    })
    List<School> getAll();
}