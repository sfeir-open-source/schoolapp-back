package com.sfeiropensource.schoolapp.repository;

import com.sfeiropensource.schoolapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends MongoRepository<User, Integer> {

    Optional<User> findByIdun(int idun);

    boolean existsByIdun(int idun);

    void deleteByIdun(int idun);

}