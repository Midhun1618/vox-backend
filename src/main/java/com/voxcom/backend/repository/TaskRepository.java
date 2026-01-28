package com.voxcom.backend.repository;

import com.voxcom.backend.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserEmail(String userEmail);

    List<Task> findByDoneFalseAndCreatedAtBefore(Date time);
}
