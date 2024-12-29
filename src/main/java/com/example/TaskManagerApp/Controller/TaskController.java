package com.example.TaskManagerApp.Controller;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TaskManagerApp.Model.Task;
import com.example.TaskManagerApp.Service.TaskService;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/tasks")
public class TaskController  {
   @Autowired
   private TaskService taskService;
   
  @PostMapping 
   public Task createTask(@RequestBody Task task, Principal principal){ 
    String loggedInUsername = principal.getName();

    if (!task.getUserId().equals(loggedInUsername)) {
        throw new RuntimeException("User ID does not match the logged-in user");
    }
    return taskService.createTask(task);
   }

   @GetMapping("/user/{id}")
   public List<Task> getTasksByUserId(@PathVariable String id){
        return taskService.getTasksbyUserId(id);
   }

   @PutMapping("/{id}")
   public Task updateTask(@PathVariable String id, @RequestBody Task task){ 
        return taskService.updateTask(id, task);
   }

   @DeleteMapping("/{id}")
   public void deleteTask(@PathVariable String id){ 
        taskService.deleteTask(id);
   }
}
