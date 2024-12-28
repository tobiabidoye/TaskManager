package com.example.TaskManagerApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManagerApp.Model.Task;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository <Task, String>{

    List<Task> findByUserId(String id);
}