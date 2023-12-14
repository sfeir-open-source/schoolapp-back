package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.entity.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository
        extends MongoRepository<School, Integer> {

    Optional<School> findByIdun(int idun);

    boolean existsByIdun(int idun);

    void deleteByIdun(int idun);
}