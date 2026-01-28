package com.voxcom.backend.repository;

import com.voxcom.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // email is the primary key
}
