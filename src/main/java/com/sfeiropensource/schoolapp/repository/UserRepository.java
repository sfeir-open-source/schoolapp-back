package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository
        extends MongoRepository<User, Integer> {
}