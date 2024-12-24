package com.example.TaskManagerApp.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
   private String id; 
   private String username; 
   private String password;
   private String role; 
   
    public  User(){ 
        
    }
    public User(String id, String username, String password, String role){ 
        this.id = id; 
        this.username = username; 
        this.password = password; 
        this.role = role; 
    }

    public void setUsername(String username){ 
        this.username = username; 
    }

    public String getUsername(){ 
        return this.username;
    }

    public void setPassword(String password){ 
        this.password = password;

    }
    public String getPassword(){ 
        return this.password;
    }

    public void setRole(String role){ 
        this.role = role; 
    }

    public String getRole(){ 
        return this.role;
    }


    public void setId(String id){ 
        this.id = id;
    }

    public String getId(){  
        return this.id;
    }

}
