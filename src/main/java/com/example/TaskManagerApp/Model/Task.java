package com.example.TaskManagerApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    @Id
    private String id; 
    private String title; 
    private String description; 
    private String status; 
    private String category; 
    private String userId;

 }
