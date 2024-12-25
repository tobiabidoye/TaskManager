package com.example.TaskManagerApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.TaskManagerApp.Service.UserService;
import com.example.TaskManagerApp.Model.User;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
   @Autowired
   UserService userService;
   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public User createUser(@RequestBody User user){ 
        System.out.println("Received User: " + user);
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());
        return userService.createUser(user);   
   }

   //get user by id and username

   @GetMapping("/{id}")
   public User getUserById(@PathVariable String id){ 
        return userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id: " + id));
   }

   @GetMapping("/username/{username}")
   public User getUserByUsername(@PathVariable String username){ 
        return userService.getUserByUsername(username) 
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with username: " + username));
    }
}
