package com.example.TaskManagerApp.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagerApp.Model.User;
import com.example.TaskManagerApp.Repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setId(UUID.randomUUID().toString());
        System.out.println("creating user");
        return userRepository.save(user);
    }
    //optional as user may not exist
    public Optional<User> getUserById(String id){ 
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username){ 
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
