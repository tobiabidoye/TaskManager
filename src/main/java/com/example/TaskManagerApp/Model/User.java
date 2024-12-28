package com.example.TaskManagerApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Document(collection = "users")




public class User {
    @Id
    private String id; 
    private String username; 
    private String password;
    private List <String> roles;
    
    User(String id, String username, String password, List <String> roles){
        this.id = id; 
        this.username = username; 
        this.password = password;
        this.roles = roles;
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
    public List<String> getRoles() { return this.roles; }
    public void setRole(List <String> roles) { this.roles = roles; } 
    

    
}
