package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends MongoRepository<User, Integer> {
}