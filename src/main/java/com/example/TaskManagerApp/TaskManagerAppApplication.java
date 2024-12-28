package com.example.TaskManagerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.TaskManagerApp")
public class TaskManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerAppApplication.class, args);
	}

}
