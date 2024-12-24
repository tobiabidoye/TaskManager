package com.example.TaskManagerApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.TaskManagerApp.Model.User;
public interface UserRepository extends MongoRepository <User,String> {
   User findByUsername(String username); 
}
