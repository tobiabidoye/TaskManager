package com.example.TaskManagerApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManagerApp.Model.User;
@Repository
public interface UserRepository extends MongoRepository <User,String> {
   User findByUsername(String username); 
}
