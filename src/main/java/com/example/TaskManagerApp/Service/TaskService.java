package com.example.TaskManagerApp.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagerApp.Model.Task;
import com.example.TaskManagerApp.Repository.TaskRepository;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
   @Autowired
   private TaskRepository taskRepository;
   
   public Task createTask(Task task){
        task.setId(UUID.randomUUID().toString());
        System.out.println("Saving task: " + task); 
        return taskRepository.save(task); 
   }

   public List<Task> getTasksbyUserId(String userId){
        return taskRepository.findByUserId(userId);
   }

   public Task updateTask(String id, Task taskDetails){ 
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found" + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCategory(taskDetails.getCategory());
        task.setStatus(taskDetails.getStatus());
        System.out.println("Creating task with ID: " + task.getId());
        return taskRepository.save(task);
   }

   public void deleteTask(String id){ 
        taskRepository.deleteById(id);
   }
}
