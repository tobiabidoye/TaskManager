package com.example.TaskManagerApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.TaskManagerApp.Model.Task;
import java.util.List;


public interface TaskRepository extends MongoRepository <Task, String>{

    List<Task> findByUserId(String userId);
}