package com.example.TaskManagerApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
   @GetMapping
   public ResponseEntity<String> adminEndpoint(){ 
        return ResponseEntity.ok("Welcome to the admin endpoint");
   } 
}
