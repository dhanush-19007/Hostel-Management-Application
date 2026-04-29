# Hostel Management Backend

Backend-only Spring Boot project generated to match the previously generated React frontend.

## Tech stack
- Spring Boot 3.3.5
- Java 21
- Maven
- PostgreSQL
- Spring Security
- Spring Data JPA
- Validation
- WebSocket
- No Lombok

## Run in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Open this project folder.
3. Set Project SDK to Java 21.
4. Let Maven import dependencies.
5. Create PostgreSQL database:
   ```sql
   CREATE DATABASE hostel_db;
   ```
6. Update DB credentials in `src/main/resources/application.properties`.
7. Run `HostelManagementApplication.java`.
8. Backend starts on `http://localhost:8080`.

## Frontend mapping
This backend exposes these API paths used by the React frontend:
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/admin/dashboard`
- `GET /api/admin/rooms`
- `POST /api/student/room-requests`
- `GET /api/student/room-requests`
- `POST /api/student/complaints`
- `GET /api/student/complaints`
- `GET /api/admin/complaints`
- `GET /api/staff/complaints`
- `GET /api/student/payments`
- `GET /api/admin/payments`

## Notes
- This version avoids Lombok completely.
- The structure is feature-based for easier maintenance.
- Repositories and services are inside their related feature packages, not in global folders.
