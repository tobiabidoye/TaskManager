package com.example.TaskManagerApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")




public class User {
    @Id
    private String id; 
    private String username; 
    private String password;
    private String role;
    
    User(String id, String username, String password, String role){
        this.id = id; 
        this.username = username; 
        this.password = password;
        this.role = role;
    }    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() {
        System.out.println("get username called"); 
        System.out.println(username); 
        return username; 
    }
    public void setUsername(String username) { 
        System.out.println("Setting username: " + username); 
        this.username = username; 
    
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; } 
    

    
}
