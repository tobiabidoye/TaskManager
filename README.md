Task Tracker Application

A secure and efficient task management application built with Spring Boot and MongoDB, enabling users to manage tasks with features such as authentication, role-based access control, and data persistence.

Features

User Management: Register, log in, and authenticate users with secure JWT-based authentication.
Task Management:
Create, update, view, and delete tasks.
Filter tasks by user ID for personalized task views.
Role-Based Access Control:
Restrict access to task management features based on user roles.
Security:
JWT-based authentication for secure API access.
Passwords hashed with BCrypt for enhanced security.
Database:
Data persistence using MongoDB, a NoSQL database for scalable storage.
Tech Stack

Backend: Spring Boot (Java)
Database: MongoDB
Authentication: JWT (JSON Web Token)
Security: BCrypt Password Hashing
Containerization: Docker
Build Tool: Maven

Installation

Clone the Repository:
git clone https://github.com/tobiabidoye/TaskManager/tree/main
cd task-tracker-application

Set Up MongoDB:
Ensure MongoDB is installed and running locally or provide a connection string to a remote MongoDB instance.

Set Up Environment Variables: Create an .env file in the root directory with the following values:
JWT_SECRET=<your-secret-key>
MONGODB_URI=mongodb://localhost:27017/tasktracker

Build and Run the Application:

mvn clean install
mvn spring-boot:run

Access the API:
The application runs on http://localhost:8080.

API Endpoints

Authentication
POST /auth/register - Register a new user.
POST /auth/login - Log in to get a JWT token.
Task Management
POST /tasks - Create a new task.
GET /tasks/user/{id} - Get tasks for a specific user.
PUT /tasks/{id} - Update a task.
DELETE /tasks/{id} - Delete a task.

Register a User

curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "password": "password123",
  "roles": ["USER"]
}'

Login
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "password": "password123"
}'

Create a task

curl -X POST http://localhost:8080/tasks \
-H "Authorization: Bearer <your-jwt-token>" \
-H "Content-Type: application/json" \
-d '{
  "title": "Complete Spring Boot Project",
  "description": "Work on the task tracker application",
  "status": "Pending",
  "category": "Development",
  "userId": "john_doe"
}'

