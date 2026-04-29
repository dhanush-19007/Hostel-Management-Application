# Hostel Management System

A full-stack PG and Hostel Room Allocation and Complaint Management System built for:
- Backend: Spring Boot 3 + Java 21
- Frontend: React + Vite
- Database: PostgreSQL

This version is generated without Lombok to avoid annotation-processing and IDE dependency issues in IntelliJ IDEA. It is structured for running:
- Backend in IntelliJ IDEA
- Frontend in VS Code

## Features
- JWT-ready authentication flow
- Room booking request module
- Complaint filing with photo upload endpoint
- Complaint status tracking
- Payment tracking
- Admin dashboard summary endpoint
- WebSocket configuration for real-time updates
- 48-hour complaint escalation scheduler

## Project structure
- `backend/` Spring Boot project
- `frontend/` React Vite project
- `postman/` Postman collection
- `database/` SQL schema and seed notes

## Backend setup in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Choose **Open** and select the `backend` folder.
3. Ensure Project SDK is set to **Java 21**.
4. Wait for Maven import to complete.
5. Create PostgreSQL database named `hostel_db`.
6. Update database username and password in:
   `backend/src/main/resources/application.properties`
7. Run the main class:
   `com.hostel.HostelManagementApplication`
8. Backend starts on:
   `http://localhost:8080`

## Frontend setup in VS Code
1. Open VS Code.
2. Open the `frontend` folder.
3. Run:
   ```bash
   npm install
   npm run dev
   ```
4. Frontend starts on:
   `http://localhost:5173`

## PostgreSQL setup
Create DB:
```sql
CREATE DATABASE hostel_db;
```

## API test with Postman
Import:
- `postman/hostel-management.postman_collection.json`

## Notes
- JWT is scaffolded with placeholder token flow for quick project startup.
- You can extend repositories, services, and DTO validation for production use.
