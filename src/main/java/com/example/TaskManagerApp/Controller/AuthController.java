package com.example.TaskManagerApp.Controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TaskManagerApp.Model.User;
import com.example.TaskManagerApp.Security.JwtUtil;
import com.example.TaskManagerApp.Service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        Optional <User> existingUser = userService.getUserByUsername(user.getUsername());
        if(existingUser.isPresent()){ 
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username already exists");
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userService.createUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully"); 

    }


    //login endpoint 
    @PostMapping("/login")
    
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){ 
        //find username 
        User user = userService.getUserByUsername(loginRequest.getUsername())
                    .orElseThrow( () -> new RuntimeException("Invalid username or password"));

        if(!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new LoginResponse(token));

    }

    public static class LoginRequest{ 
        private String username; 
        private String password; 

        public String getUsername(){ 
            return username;
        }
        
        public void setUsername(String name){
            username = name;
        }

        public String getPassword(){ 
            return password;
        }

        public void setPassword(String passcode){
            password = passcode;
        } 
    }

    public static class LoginResponse{

        private String token;
        public LoginResponse(String token){
            this.token = token;
        }
        
        public String getToken(){ 
            return token;
        }
    }
    
}

